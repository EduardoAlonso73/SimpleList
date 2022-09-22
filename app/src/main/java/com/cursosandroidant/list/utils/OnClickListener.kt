package com.cursosandroidant.list.utils

import com.cursosandroidant.list.Model.ItemEntity

interface OnClickListener {
    fun onClick(itemEntity: ItemEntity)
    fun onLongClick(itemEntity: ItemEntity)
}