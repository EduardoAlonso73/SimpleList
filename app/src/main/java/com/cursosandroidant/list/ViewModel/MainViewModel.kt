package com.cursosandroidant.list.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosandroidant.list.Model.ItemEntity


class MainViewModel : ViewModel() {
    private var interactor = MainInteract()
    private  var changeTextInpText=MutableLiveData<String>()

    val items = interactor.items

    val errorMsg: MutableLiveData<String> = MutableLiveData()


    fun addItem(itemEntity: ItemEntity){
        try {
            interactor.addItem(itemEntity)
        } catch (e: Exception) {
            errorMsg.value = e.message
        }
    }

    fun updateItem(itemEntity: ItemEntity){
        try {
            interactor.updateItem(itemEntity)
        } catch (e: Exception) {
            errorMsg.value = e.message
        }
    }
    fun deleteItem(itemEntity: ItemEntity){
        try {
            interactor.deleteItem(itemEntity)
        } catch (e: Exception) {
            errorMsg.value = e.message
        }
    }

    //=-=-=- GET AND SET -=-=-=-
    fun setChangeTextInpText(text:String) {
        changeTextInpText.value = text
    }
    fun getChangeTextInpText():LiveData<String> = changeTextInpText



}