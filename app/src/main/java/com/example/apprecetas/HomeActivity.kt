package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecetas.adapter.MainCategoryAdapter
import com.example.apprecetas.adapter.SubCategoryAdapter
import com.example.apprecetas.database.RecipeDatabase

import com.example.apprecetas.entities.CategoryItems
import com.example.apprecetas.entities.MealsItems

import kotlinx.coroutines.launch


class HomeActivity : BaseActivity() {

    private lateinit var rvMainCategory: RecyclerView
    private lateinit var rvSubCategory: RecyclerView
    private lateinit var tvCategory: TextView

    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Vincula los RecyclerView con sus IDs
        rvMainCategory = findViewById(R.id.rv_main_category)
        rvSubCategory = findViewById(R.id.rv_sub_category)
        tvCategory = findViewById(R.id.tvCategory)


        getDataFromDb()

        mainCategoryAdapter.setClickListener(onCliked)
        subCategoryAdapter.setClickListener(onClikedSubItem)

    }

    private val onCliked = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onClikedSubItem = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id:Int) {
            var intent =Intent(this@HomeActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()

                if (arrMainCategory.isNotEmpty()) {
                    getMealDataFromDb(arrMainCategory[0].strcategory)
                }

                mainCategoryAdapter.setData(arrMainCategory)
                rvMainCategory.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rvMainCategory.adapter = mainCategoryAdapter
            }
        }
    }

    private fun getMealDataFromDb(categoryName:String){
        tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)
                rvSubCategory.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rvSubCategory.adapter = subCategoryAdapter
            }
        }
    }
}