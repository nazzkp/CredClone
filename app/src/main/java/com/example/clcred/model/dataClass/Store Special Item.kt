package com.example.clcred.model.dataClass

import com.example.clcred.R

data class StoreSpecialItem(
    val imageRes: Int,
    val name: String = "",
    val brand: String = "",
    val price: Int = 0,
    val oldPrice: Int = 0,
    val discount: Int,
    val brandImage: Int = 0,
)

val brandFocusList = listOf(
    StoreSpecialItem(
        imageRes = R.drawable.coffee,
        discount = 53,
        brandImage = R.drawable.foot_locker
    ),
    StoreSpecialItem(
        imageRes = R.drawable.sunglass,
        discount = 60,
        brandImage = R.drawable.adonit
    ),
    StoreSpecialItem(
        imageRes = R.drawable.tshirt,
        discount = 24,
        brandImage = R.drawable.ubiquiti
    ),
    StoreSpecialItem(
        imageRes = R.drawable.romantic,
        discount = 53,
        brandImage = R.drawable.foot_locker
    )
)

val storeSpecialItems = listOf(
    StoreSpecialItem(
        imageRes = R.drawable.badam,
        name = "Kashmiri Kagzi Badam",
        brand = "KesarCo",
        price = 195,
        oldPrice = 350,
        discount = 53,
    ), StoreSpecialItem(
        imageRes = R.drawable.cardamom,
        name = "Whole Green Cardamom",
        brand = "KesarCo",
        price = 129,
        oldPrice = 165,
        discount = 35,
    ), StoreSpecialItem(
        imageRes = R.drawable.cashew,
        name = "Whole Cashews | W320 Grade",
        brand = "KesarCo",
        price = 199,
        oldPrice = 320,
        discount = 65,
    ), StoreSpecialItem(
        imageRes = R.drawable.figs,
        name = "Dried Afghani Figs",
        brand = "KesarCo",
        price = 75,
        oldPrice = 200,
        discount = 60,
    ), StoreSpecialItem(
        imageRes = R.drawable.peanuts,
        name = "Raw Peanuts ",
        brand = "KesarCo",
        price = 40,
        oldPrice = 120,
        discount = 30,
    ), StoreSpecialItem(
        imageRes = R.drawable.pepper,
        name = "Black Pepper",
        brand = "KesarCo",
        price = 250,
        oldPrice = 450,
        discount = 45,
    ), StoreSpecialItem(
        imageRes = R.drawable.pinksalt,
        name = "Himalayan Pink Salt",
        brand = "KesarCo",
        price = 50,
        oldPrice = 225,
        discount = 77,
    ), StoreSpecialItem(
        imageRes = R.drawable.pistachio,
        name = "Pistachios (Salted & Roasted)",
        brand = "KesarCo",
        price = 450,
        oldPrice = 1200,
        discount = 48,
    ), StoreSpecialItem(
        imageRes = R.drawable.badam,
        name = "Kashmiri Kagzi Badam",
        brand = "KesarCo",
        price = 195,
        oldPrice = 350,
        discount = 53,
    )
)