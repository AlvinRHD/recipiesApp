package com.example.apprecetas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.apprecetas.databinding.ActivityDetailBinding
import com.example.apprecetas.models.RecipeModel
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetailBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        val recipeId = intent.getStringExtra("RECIPE_ID")

        if (recipeId != null) {
            loadRecipeDetails(recipeId)
        } else {
            Toast.makeText(this, "Error al cargar detalles", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadRecipeDetails(recipeId: String) {
        firestore.collection("recipes").document(recipeId)
            .get()
            .addOnSuccessListener { document ->
                val recipe = document.toObject(RecipeModel::class.java)
                if (recipe != null) {
                    binding.tvCategory.text = recipe.title
                    Glide.with(this).load(recipe.imageUrl).into(binding.imgRecipe)
                    binding.tvIngredientes.text = recipe.ingredients.joinToString("\n")
                    binding.tvServing.text = "${recipe.servings} personas"
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar receta", Toast.LENGTH_SHORT).show()
            }
    }
}