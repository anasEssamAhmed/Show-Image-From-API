package com.example.showimagefromapi.api

import com.example.showimagefromapi.model.ImagePost
import retrofit2.Response
import retrofit2.http.GET

interface ImageInterface {

    @GET("photos")
    suspend fun getImagePost() : Response<List<ImagePost>>
}