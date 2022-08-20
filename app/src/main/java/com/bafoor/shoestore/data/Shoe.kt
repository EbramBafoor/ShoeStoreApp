package com.bafoor.shoestore.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class Shoe(
    var shoesName: String,
    var company: String,
    var shoesSize: String,
    var description: String,
):Parcelable
