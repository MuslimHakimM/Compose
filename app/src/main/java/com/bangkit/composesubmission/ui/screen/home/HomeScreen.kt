package com.bangkit.composesubmission.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.composesubmission.di.Injection
import com.bangkit.composesubmission.model.FavMotor
import com.bangkit.composesubmission.ui.components.StateUi
import com.bangkit.composesubmission.ui.general.ItemMotor
import com.bangkit.composesubmission.ui.viewmodel.HomeViewModel
import com.bangkit.composesubmission.ui.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = StateUi.Loading).value.let { uiState ->
        when (uiState) {
            is StateUi.Loading -> {
                viewModel.getAllRewards()
            }

            is StateUi.Success -> {
                HomeContent(
                    favoriteMotor = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }

            is StateUi.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    favoriteMotor: List<FavMotor>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(favoriteMotor) { data ->
                ItemMotor(
                    image = data.motor.image,
                    name = data.motor.name,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.motor.id)
                    }
                )
            }
        }
}

//    Column {
//        LazyColumn(
 //           modifier = modifier.fillMaxSize(),
  //      ) {
  //          items(favoriteMotor) { data ->
 //               ItemMotor(
//                    image = data.motor.image,
//                    name = data.motor.name,
//                   modifier = modifier.clickable {
 //                       navigateToDetail(data.motor.id)
 //                   }
 //               )
//
 //           }
  //      }
  //  }
//}

