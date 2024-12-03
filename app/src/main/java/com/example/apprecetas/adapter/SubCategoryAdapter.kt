package com.example.apprecetas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprecetas.R
import com.example.apprecetas.entities.MealsItems
import com.example.apprecetas.entities.Recipes


class SubCategoryAdapter:RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    var ctx : Context? = null
    var arrSubCategory = ArrayList<MealsItems>()

    // ViewHolder
    class RecipeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvDishName: TextView = view.findViewById(R.id.tv_dish_name)
        val imgDish: ImageView = view.findViewById(R.id.img_dish)
    }

    // Establecer los datos
    fun setData(arrData : List<MealsItems>){
        arrSubCategory =  ArrayList(arrData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category, parent, false))
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val meal = arrSubCategory[position]

        // Configurar el nombre del platillo
        holder.tvDishName.text = meal.strMeal ?: "Sin nombre"

        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.imgDish)

    }
}