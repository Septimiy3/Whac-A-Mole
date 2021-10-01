package com.example.whac_a_mole

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val selected = MutableLiveData<String>()
    fun select(item: String) {
        selected.value = item
    }

    fun getSelected(): LiveData<String> {
        selected.value
        return selected
    }
}