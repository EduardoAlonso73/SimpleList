package com.cursosandroidant.list.ViewModel
import androidx.lifecycle.MutableLiveData
import com.cursosandroidant.list.Model.ItemEntity


class MainInteract {

    val items: MutableLiveData<MutableList<ItemEntity>> by lazy {MutableLiveData<MutableList<ItemEntity>> ()  }
    lateinit var  collectionDta:MutableList<ItemEntity>
    init { this.items.value = mutableListOf()}

    fun addItem(itemEntity: ItemEntity) {
        this.items.value
        this.items.value?.let {
            collectionDta = mutableListOf(itemEntity)
            collectionDta.addAll(it)
            this.items.value = collectionDta
        }
    }

    fun deleteItem(itemEntity: ItemEntity) {
        items.value?.let {
            val index = collectionDta.indexOf(itemEntity)
            if (index != -1) {
                collectionDta.removeAt(index)
                this.items.value = collectionDta
            }
        }
    }

    fun updateItem(itemEntity: ItemEntity) {
        items.value?.let {
            val index = collectionDta.indexOf(itemEntity)
            if (index != -1) {
                collectionDta[index] = itemEntity
                this.items.value = collectionDta
            }
        }

    }
}
