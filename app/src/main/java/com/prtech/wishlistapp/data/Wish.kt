package com.prtech.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description : String = "",
)

/*object DummyData{
    val wishList = listOf(
        Wish(title = "Iphone 18 pro max", description = "i want this phone 😟😟"),
        Wish(title = "macbook pro max", description = "i want this laptop😟😟"),
        Wish(title = "bmw ", description = "i want this car😟😟"),
        Wish(title = "a good look", description = "i want this for you!"),
    )
}*/