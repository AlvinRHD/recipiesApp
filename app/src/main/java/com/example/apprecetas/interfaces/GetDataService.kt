package com.example.apprecetas.interfaces


import retrofit2.Call
import com.example.apprecetas.entities.Category
import retrofit2.http.GET

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>
}