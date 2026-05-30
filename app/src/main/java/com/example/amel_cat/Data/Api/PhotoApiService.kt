package com.example.amel_cat.Data.Api

import com.example.amel_cat.Data.Model.PhotoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    // Sesuai modul: mengambil list data dari https://picsum.photos/v2/list
    @GET("v2/list")
    fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 30
    ): Call<List<PhotoModel>>
}