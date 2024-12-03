package com.example.apprecetas.interfaces


import retrofit2.Call
import com.example.apprecetas.entities.Category
import com.example.apprecetas.entities.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

}