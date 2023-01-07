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
    private var lastLoadedUserList: ArrayList<User> = arrayListOf()

    private val searchResultEmptyMutable = MutableLiveData<Any?>()
    val searchResultEmpty: LiveData<Any?> = searchResultEmptyMutable
    private val searchResultNotEmptyMutable = MutableLiveData<Any?>()
    val searchResultNotEmpty: LiveData<Any?> = searchResultNotEmptyMutable

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

    fun filterListByString(query: String, requiredDepartmentListFilter: Department) {
        if (!isBackgroundLoadAllowed){
            scheduledUnitsList.add { filterListByString(query, requiredDepartmentListFilter) }
            return
        }

        if (requiredDepartmentListFilter::class == requiredUserDepartment::class && lastLoadedUserList.isNotEmpty()) {
            val filteredList: ArrayList<User> = arrayListOf()

            // Do not filter list if the query is empty or blank
            if (query.isBlank() || query.isEmpty()){
                // Do not send new list to adapter if there wasn't any queries before
                if (lastLoadedUserList.size != userListMutable.value?.size) {
                    filteredList.addAll(lastLoadedUserList)
                    userListMutable.value = filteredList
                }
                searchResultNotEmptyMutable.value = null
                return
            }

            lastLoadedUserList.forEach {
                if ((it.getFullName().contains(query, true)) || it.userTag.contains(query, true)){
                    filteredList.add(it)
                }
            }

            if (filteredList.isEmpty()){
                searchResultEmptyMutable.value = null
            } else {
                searchResultNotEmptyMutable.value = null
            }

            userListMutable.value = filteredList
        }
    }

    fun updateUserList(){
        getUserList()
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

        lastLoadedUserList.clear()
        lastLoadedUserList.addAll(userList)

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