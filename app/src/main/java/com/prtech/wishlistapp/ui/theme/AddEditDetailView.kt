package com.prtech.wishlistapp.ui.theme

import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.prtech.wishlistapp.R
import com.prtech.wishlistapp.data.Wish
import com.prtech.wishlistapp.ui.theme.WishViewModel
import kotlinx.coroutines.launch


@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    val snackmessage = remember{ mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope ()
    if (id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDiscriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDiscriptionState = ""
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(R.string.update_wish) else stringResource(
                    R.string.add_wish
                )
            )
            { navController.navigateUp()}
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChange(it)
                } )
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "discription",
                value = viewModel.wishDiscriptionState,
                onValueChanged = {
                    viewModel.onWishDiscriptionState(it)
                } )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDiscriptionState.isNotEmpty()) {
                      if(id != 0L){
                          viewModel.updateWish(
                              Wish(
                                  id = id,
                                  title = viewModel.wishTitleState.trim(),
                                  description = viewModel.wishDiscriptionState.trim()
                              )
                          )
                      }else{
                          viewModel.addWish(
                              Wish(
                                  title = viewModel.wishTitleState.trim(),
                                  description = viewModel.wishDiscriptionState.trim(),
                              )
                          )
                      }
                        navController.navigateUp()
                        scope.launch {
                            snackbarHostState.showSnackbar("Wish has been created")

                        }

                    }else
                    {
                        scope.launch {
                            navController.navigateUp()
                            snackbarHostState.showSnackbar("Enter fields to create a wish")
                        }

                    }
                },
            ) {
                Text(
                    text = if(id != 0L) stringResource(R.string.update_wish) else stringResource(R.string.add_wish),
                    style = TextStyle(
                        fontSize = 18.sp
                    )

                )
            }

        }

    }
}
@Composable
fun WishTextField(
    label: String,
    value : String,
    onValueChanged : (String) -> Unit,
){
    OutlinedTextField(value = value, onValueChange = onValueChanged
    , label = { Text(text = label , color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Red,

        )
        // change the outline color and focus and unfouss boarder color
        )
}

