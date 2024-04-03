package com.mexiti.picphotoapp.model

import kotlinx.serialization.Serializable

@Serializable
data class PicModel(
    val id: String,
    val author: String,
    val url : String,
    val width : Int,
    val height: Int,
    val download_url: String,
    )
