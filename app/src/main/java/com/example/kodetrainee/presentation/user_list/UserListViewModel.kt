package com.example.kodetrainee.presentation.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kodetrainee.domain.model.User
import com.example.kodetrainee.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val disposableBag = CompositeDisposable()

    private var disposableGetUserList: Disposable? = null
    private val userListMutable = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = userListMutable

    init {
        getUserList()
    }

    private fun getUserList() {
        disposableGetUserList?.dispose()

        disposableGetUserList = userRepository.getUserList()
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