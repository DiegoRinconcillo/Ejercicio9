package com.mexiti.picphotoapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mexiti.picphotoapp.model.PicModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL= "https://picsum.photos"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType() ) )
    .baseUrl(BASE_URL)
    .build()

interface PicApiService {
    @GET("v2/list?limit=20")
    suspend fun getPhotos():List<PicModel>
}

//Patron de diseño Singleton

object PicApi{
    val retrofitService: PicApiService by lazy {
        retrofit.create(PicApiService::class.java)
    }
}

