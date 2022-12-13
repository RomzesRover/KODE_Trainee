package com.example.kodetrainee.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val backgroundHeavyLoadMutable = MutableLiveData<Boolean>().apply { value = true }
    val backgroundHeavyLoad: LiveData<Boolean> = backgroundHeavyLoadMutable

    fun allowBackgroundHeavyLoad(){
        if (backgroundHeavyLoadMutable.value == false)
            backgroundHeavyLoadMutable.value = true
    }

    fun rejectBackgroundHeavyLoad(){
        if (backgroundHeavyLoadMutable.value == true)
            backgroundHeavyLoadMutable.value = false
    }
}