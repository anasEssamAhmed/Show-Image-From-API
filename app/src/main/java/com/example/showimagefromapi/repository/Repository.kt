package com.example.showimagefromapi.repository

import com.example.showimagefromapi.api.ImagePostInstance
import com.example.showimagefromapi.model.ImagePost
import retrofit2.Response

class Repository {

    suspend fun getImagePost() : Response<List<ImagePost>> {
        return ImagePostInstance.api.getImagePost()
    }
}