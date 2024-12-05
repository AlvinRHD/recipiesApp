package com.example.apprecetas.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprecetas.R
import com.example.apprecetas.databinding.ItemRvMainCategoryBinding
import com.example.apprecetas.models.RecipeModel

class RecipeAdapter(
    private val categories: List<String>, // Lista de categorías únicas
    private val onCategoryClick: (String) -> Unit // Acción al hacer clic en una categoría
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRvMainCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class RecipeViewHolder(private val binding: ItemRvMainCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.tvCategory.text = category
            binding.imgRecipe.setImageResource(R.drawable.ic_category_placeholder) // Icono para la categoría

            itemView.setOnClickListener {
                onCategoryClick(category) // Enviar el nombre de la categoría al hacer clic
            }
        }
    }
}

