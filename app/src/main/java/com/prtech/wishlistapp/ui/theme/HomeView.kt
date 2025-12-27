package com.prtech.wishlistapp.ui.theme

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prtech.wishlistapp.ui.theme.Screen
import com.prtech.wishlistapp.ui.theme.WishViewModel
import com.prtech.wishlistapp.data.Wish

@Composable
fun HomeView(navController: NavController,
             viewModel: WishViewModel
) {
    val context = LocalContext.current
    Scaffold(
            topBar = {
                AppBarView(title = "WishListApp", {
                })
            },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                containerColor = Color.Magenta,
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    "noting"
                )

            }
        }
    ) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
        ) {
            items(wishlist.value , key= {wish -> wish.id}) { wish ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {dismissValue ->
                        if(dismissValue == SwipeToDismissBoxValue.EndToStart || dismissValue == SwipeToDismissBoxValue.StartToEnd){
                            viewModel.deleteWish(wish)
                        }
                        true
                    },
                    positionalThreshold = {it * 0.25f}
                )

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = true,
                    enableDismissFromEndToStart = false,
                    backgroundContent = {
                        val color by animateColorAsState(
                            targetValue = when(dismissState.targetValue){
                                SwipeToDismissBoxValue.EndToStart -> Color.Red.copy(alpha = 0.8f)
                                SwipeToDismissBoxValue.StartToEnd -> Color.Red.copy(alpha = 0.8f)
                                SwipeToDismissBoxValue.Settled -> Color.Transparent
                            },
                            label = "background color animation"
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White
                            )
                        }
                    }
                ) {
                    WishItem(wish = wish) {
                        val id = wish.id
                        navController.navigate(Screen.AddScreen.route + "/$id")

                    }
                }


            }

        }
    }
}

@Composable
fun WishItem(wish : Wish, onClick:()-> Unit){
    Card(
        modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .clickable {
                    onClick()
                },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
        ),

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}