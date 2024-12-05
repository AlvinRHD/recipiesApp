package com.example.apprecetas.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprecetas.databinding.ItemRvMainCategoryBinding
import com.example.apprecetas.models.RecipeModel

class RecipeAdapter (
    private val recipes: List<RecipeModel>,
    private val onClick: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(private val binding: ItemRvMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeModel) {
            binding.tvDishName.text = recipe.title
            Glide.with(binding.root.context).load(recipe.imageUrl).into(binding.imgDish)
            binding.root.setOnClickListener { onClick(recipe) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRvMainCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
        // Log para depuración
        Log.d("RecipeAdapter", "Cargando receta: ${recipe.title}")
    }


    override fun getItemCount() = recipes.size
}