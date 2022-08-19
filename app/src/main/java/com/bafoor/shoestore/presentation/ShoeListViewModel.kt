package com.bafoor.shoestore.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import com.bafoor.shoestore.data.Shoe



class ShoeListViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val mShoeList = ArrayList<Shoe>()
    private var _shoeList =  MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>> =   _shoeList



    fun onEvent(event: ShoeStoreEvent){
        when(event){
            is ShoeStoreEvent.AddShoe -> {
                addNewShoe(event.shoe)
            }
        }
    }


    private fun addNewShoe(shoe: Shoe){
        savedStateHandle["shoe"] = shoe
        mShoeList.add(savedStateHandle["shoe"]!!)
        _shoeList.value = mShoeList
    }




















}