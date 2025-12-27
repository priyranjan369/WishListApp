package com.prtech.wishlistapp.ui.theme

import android.content.Context
import androidx.room.Room
import com.prtech.wishlistapp.data.Wish
import com.prtech.wishlistapp.data.WishDatabase
import com.prtech.wishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase
    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java,"wishlist.db").build()
    }
}