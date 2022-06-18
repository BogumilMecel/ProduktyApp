package com.gmail.bogumilmecel2.produkty.feature_items.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bogumilmecel2.produkty.common.presentation.components.CustomToolbar
import com.gmail.bogumilmecel2.produkty.feature_items.presentation.components.ItemLayout
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = hiltViewModel(),
    username:String = "GoPOS"
) {
    val scaffoldState = rememberScaffoldState()

    val itemsState = viewModel.itemsState.value

    LaunchedEffect(key1 = true) {
        viewModel.snackbarEvent.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(message = it)
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.accessToken.collectLatest {
            viewModel.getItems(it)
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getAccessToken()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomToolbar(title = "Welcome $username", isBackArrowVisible = false)
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(itemsState.size) {
                ItemLayout(
                    item = itemsState[it]
                )
            }
        }
    }


}