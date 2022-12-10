package com.example.kodetrainee.presentation.user_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kodetrainee.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val disposable = CompositeDisposable()

    fun testApiCall() {
        disposable.add(
            userRepository.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("Privet", it.first().firstName + " " + it.first().lastName)
                }, {

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}