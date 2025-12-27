@file:OptIn(ExperimentalMaterial3Api::class)

package com.prtech.wishlistapp.ui.theme

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AppBarView(
    title: String,
    onBackNavClicked : () -> Unit = {}
) {
    val navigationIcon : @Composable (() -> Unit) = {
        if (! title.contains("WishList")) {
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    tint = Color.White ,
                    contentDescription = null
                )
            }
        } else {
            null
        }
    }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Magenta ,
            titleContentColor = Color.White  // we use val for the modern degine to store color code
        ),

        title = {
            Text(
                text = title ,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .heightIn(max = 40.dp)
                    .shadow(elevation = 10.dp)

            )
        } ,
        navigationIcon = navigationIcon

    )
}