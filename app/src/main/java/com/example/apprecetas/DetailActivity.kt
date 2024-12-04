package com.example.apprecetas

import android.os.Bundle
import android.widget.Toast
import com.example.apprecetas.entities.Category
import com.example.apprecetas.entities.Meal
import com.example.apprecetas.interfaces.GetDataService
import com.example.apprecetas.retofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : BaseActivity() {

    var id:Int= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var id = intent.getIntExtra("id", 0)





    }

    fun getCategories(){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)
        call.enqueue(object : Callback<Meal> {
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {


            }

        })
    }

}