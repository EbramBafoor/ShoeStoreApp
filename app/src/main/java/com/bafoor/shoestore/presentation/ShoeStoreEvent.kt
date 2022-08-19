package com.bafoor.shoestore.presentation

import com.bafoor.shoestore.data.Shoe

sealed class ShoeStoreEvent{
    class AddShoe(val shoe: Shoe): ShoeStoreEvent()
}
