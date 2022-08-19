package com.bafoor.shoestore.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class Shoe(
    val shoesName: String,
    val company: String,
    val shoesSize: Int,
    val description: String,
):Parcelable
