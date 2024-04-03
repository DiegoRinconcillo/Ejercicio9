package com.mexiti.picphotoapp.ui.screens

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mexiti.picphotoapp.R
import com.mexiti.picphotoapp.model.PicModel
import com.mexiti.picphotoapp.viewmodel.PicUiState


@Composable

fun HomeScreen(
    picUiState: PicUiState,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when(picUiState){
        is PicUiState.Loading -> LoadingScreen(modifier= Modifier.fillMaxSize())
        is PicUiState.Success -> PhotosGridScreen(photos = picUiState.photos)
        is PicUiState.Error -> ErrorScreen(modifier=Modifier.fillMaxSize())
    }

}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.loader), contentDescription = "Loading")
    }
}

@Composable
fun ResultScreen(photos:String, modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Text(text = photos )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.error_load), contentDescription = "Error")

    }
}


@Composable
fun PicPhotoCard (photo: PicModel, modifier: Modifier){
    Card(
        modifier = modifier .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.download_url)
                .crossfade(true)
                .build()

            ,
            contentDescription =  stringResource(R.string.pic_image),
            modifier = modifier.clip(RoundedCornerShape(30.dp)),
            error = painterResource(id = R.drawable.error_404),
            placeholder = painterResource (id = R.drawable.carga),
            contentScale = ContentScale.Fit,

        )
    }

}


@Preview
@Composable
fun HomeScreenPreview(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        //HomeScreen(stringResource(R.string.placeholder_result))
    }

}

@Composable
fun PhotosGridScreen(
    photos : List <PicModel>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(250.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ){
        items(
            items = photos,
            key = {
                    photo -> photo.id
            }
        ){
                photo-> PicPhotoCard(photo = photo,
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
        }
    }

}


