package com.example.apprecetas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprecetas.databinding.ItemRvSubCategoryBinding
import com.example.apprecetas.models.RecipeModel

class RecipeSubAdapter (
    private val recipes: List<RecipeModel>,
    private val onClick: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeSubAdapter.RecipeSubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeSubViewHolder {
        val binding = ItemRvSubCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeSubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeSubViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeSubViewHolder(private val binding: ItemRvSubCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel) {
            binding.tvCategory.text = recipe.title // Mostrar título de la receta.
            Glide.with(itemView.context).load(recipe.imageUrl).into(binding.imgRecipe)

            itemView.setOnClickListener { onClick(recipe) }
        }
    }
}