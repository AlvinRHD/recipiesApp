package com.example.apprecetas.interfaces

import android.telecom.Call
import retrofit2.Call
import com.example.apprecetas.entities.Category

interface GetDataService {
    @GET( value "/categories.php")
    fun getCategoryList(): Call<List<Category>>
}