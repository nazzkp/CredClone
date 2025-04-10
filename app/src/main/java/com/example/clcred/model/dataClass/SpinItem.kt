package com.example.clcred.model.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clcred.R

@Entity(tableName = "CredSampleDBTable")
data class SpinItem(
    @PrimaryKey
    val imageRes: Int,
    val name: String,
    val brand: String,
    val price: Int,
    val oldPrice: Int,
    val isLive: Boolean = false,
    val launchDateTime: String = ""
)

val spinItems = listOf(
    SpinItem(
        imageRes = R.drawable.romantic,
        name = "Pure Natural Aroma Perfumes",
        brand = "Lovali",
        price = 195,
        oldPrice = 350,
        isLive = false,
        launchDateTime = "9th Apr / 9pm",
    ),
    SpinItem(
        imageRes = R.drawable.massager,
        name = "Handheld Deep Tissue Back Massager",
        brand = "OEM",
        price = 195,
        oldPrice = 350,
        isLive = true,
        launchDateTime = "9th Apr / 9pm",
    ),
    SpinItem(
        imageRes = R.drawable.socks,
        name = "Boys crew socks",
        brand = "KT",
        price = 195,
        oldPrice = 350,
        isLive = true,
        launchDateTime = "9th Apr / 9pm",
    ),
    SpinItem(
        imageRes = R.drawable.tshirt,
        name = "Girls' Long-Sleeve Cute Print T-ShirtGirls' Long-Sleeve Cute Print T-Shirt",
        brand = "Autumn",
        price = 195,
        oldPrice = 350,
        isLive = false,
        launchDateTime = "9th Apr / 9pm",
    )
)