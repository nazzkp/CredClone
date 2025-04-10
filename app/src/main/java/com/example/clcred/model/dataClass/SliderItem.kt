package com.example.clcred.model.dataClass

import com.example.clcred.R

data class SliderItem(
    val imageRes: Int,
    val name: String,
    val brand: String,
    val isFeatured : Boolean = false
)

val sliderItems = listOf(
    SliderItem(
        imageRes = R.drawable.romantic,
        name = "Pure Natural Aroma Perfumes",
        brand = "Lovali",
        isFeatured = true
    ),
    SliderItem(
        imageRes = R.drawable.massager,
        name = "Handheld Deep Tissue Back Massager",
        brand = "OEM",
    ),
    SliderItem(
        imageRes = R.drawable.socks,
        name = "Boys crew socks",
        brand = "KT",
        isFeatured = true
    ),
    SliderItem(
        imageRes = R.drawable.air_blower,
        name = "Mini Jet Turbo Fan Electric Air Blower",
        brand = "QXXZ"
    ),
    SliderItem(
        imageRes = R.drawable.diament,
        name = "Women's perfume",
        brand = "OEM/ODM"
    ),
    SliderItem(
        imageRes = R.drawable.coffee,
        name = "Replacement white Paper Coffee Filters",
        brand = "Pearl House",
        isFeatured = true
    ),
    SliderItem(
        imageRes = R.drawable.tshirt,
        name = "Girls' Long-Sleeve Cute Print T-Shirt",
        brand = "Autumn"
    ),
    SliderItem(
        imageRes = R.drawable.sunglass,
        name = "Metal Round Eyeglasses",
        brand = "Steampunk",
        isFeatured = true
    )
)