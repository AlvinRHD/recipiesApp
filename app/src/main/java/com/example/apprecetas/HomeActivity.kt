package com.example.apprecetas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecetas.adapter.MainCategoryAdapter
import com.example.apprecetas.adapter.SubCategoryAdapter
import com.example.apprecetas.entities.Recipes

class HomeActivity : BaseActivity() {

    var arrMainCategory = ArrayList<Recipes>()
    var arrSubCategory = ArrayList<Recipes>()

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
        val rvMainCategory = findViewById<RecyclerView>(R.id.rv_main_category)
        val rvSubCategory = findViewById<RecyclerView>(R.id.rv_sub_category)

        //Datos temporales
        arrMainCategory.add(Recipes(1,"Beef"))
        arrMainCategory.add(Recipes(2,"Chicken"))
        arrMainCategory.add(Recipes(3,"Dessert"))
        arrMainCategory.add(Recipes(4,"Lamb"))

        mainCategoryAdapter.setData(arrMainCategory)

        arrSubCategory.add(Recipes(1, "Beef and mustard pie"))
        arrSubCategory.add(Recipes(2, "Chicken and mustard pie"))
        arrSubCategory.add(Recipes(3, "Dessert and mustard pie"))
        arrSubCategory.add(Recipes(4, "Lamb and mustard pie"))

        subCategoryAdapter.setData(arrSubCategory)

        rvMainCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvMainCategory.adapter = mainCategoryAdapter

        rvSubCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvSubCategory.adapter = subCategoryAdapter



    }
}