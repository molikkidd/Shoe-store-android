package com.udacity.shoestore.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import kotlin.concurrent.timer

class ShoeListViewModel : ViewModel() {
    private val _listProducts = MutableLiveData<MutableList<Shoe>>()
    val listProducts: LiveData<MutableList<Shoe>>
        get() = _listProducts

    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave: LiveData<Boolean>
        get() = _eventSave

    init {
        _listProducts.value = mutableListOf()
    }

    private fun onSaveShoe() {
        _eventSave.value = true
    }

    fun savedShoe() {
        _eventSave.value = false
    }

    fun saveProduct(shoe: Shoe) {
        onSaveShoe()
            listProducts.value?.add(shoe)
            _listProducts.value = _listProducts.value

    }
}