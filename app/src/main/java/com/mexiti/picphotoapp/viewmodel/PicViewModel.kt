package com.mexiti.picphotoapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mexiti.picphotoapp.model.PicModel
import com.mexiti.picphotoapp.network.PicApi
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface PicUiState{
    data class Success(val photos:List <PicModel>): PicUiState
    object Error: PicUiState
    object Loading:PicUiState
}

class PicViewModel: ViewModel() {
    var picUiState:PicUiState by mutableStateOf(PicUiState.Loading)
        private set

    init{
        getPicPhotos()
    }


    fun getPicPhotos(){
        viewModelScope.launch{
            picUiState = try{
                val listResult = PicApi.retrofitService.getPhotos()
                PicUiState.Success(listResult)
            }catch(e:IOException){
                PicUiState.Error
            }


        }
    }
}