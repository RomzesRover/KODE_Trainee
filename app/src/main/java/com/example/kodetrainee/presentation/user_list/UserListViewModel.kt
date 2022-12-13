package com.example.kodetrainee.presentation.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.domain.model.User
import com.example.kodetrainee.presentation.user_list.UserListFragment.Companion.REQUIRED_DEPARTMENT_KEY
import com.example.kodetrainee.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository, private val state: SavedStateHandle): ViewModel() {

    private val disposableBag = CompositeDisposable()

    private var disposableGetUserList: Disposable? = null
    private val userListMutable = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = userListMutable

    private lateinit var requiredUserDepartment: Department

    private var isBackgroundLoadAllowed = true
    private val scheduledUnitsList: ArrayList<() -> (Unit)> = arrayListOf()

    init {
        getRequiredUserDepartment()
        getUserList()
    }

    fun setIsBackgroundLoadAllowed(allowed: Boolean){
        isBackgroundLoadAllowed = allowed

        if (allowed){
            scheduledUnitsList.forEach {
                it.invoke()
            }
            scheduledUnitsList.clear()
        }
    }

    private fun getRequiredUserDepartment(){
        requiredUserDepartment = state.get<Department>(REQUIRED_DEPARTMENT_KEY) ?: Department.All()
    }

    private fun getUserList() {
        if (!isBackgroundLoadAllowed){
            scheduledUnitsList.add { getUserList() }
            return
        }

        disposableGetUserList?.dispose()

        disposableGetUserList = userRepository.getUserList(requiredUserDepartment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccessLoadUserList(it)
            }, {
                onFailureLoadUserList(it)
            })

        disposableBag.add(disposableGetUserList!!)
    }

    private fun onSuccessLoadUserList(userList: List<User>){
        if (!isBackgroundLoadAllowed){
            scheduledUnitsList.add { onSuccessLoadUserList(userList) }
            return
        }

        userListMutable.value = userList
    }

    private fun onFailureLoadUserList(throwable: Throwable){
        throwable.printStackTrace()
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }
}