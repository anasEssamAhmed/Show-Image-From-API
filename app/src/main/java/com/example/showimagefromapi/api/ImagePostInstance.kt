package com.example.showimagefromapi.api

import com.example.showimagefromapi.utli.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImagePostInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:ImageInterface by lazy {
        retrofit.create(ImageInterface :: class.java)
    }

}