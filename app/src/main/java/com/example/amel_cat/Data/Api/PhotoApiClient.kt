package com.example.amel_cat.Data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotoApiClient {
    private const val BASE_URL = "https://picsum.photos/"

    val instance: PhotoApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PhotoApiService::class.java)
    }
}