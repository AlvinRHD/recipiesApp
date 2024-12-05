package com.example.apprecetas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.apprecetas.databinding.ActivityDetailBinding
import com.example.apprecetas.models.RecipeModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getParcelableExtra<RecipeModel>("recipe")

        recipe?.let {
            // Asigna datos a las vistas
            binding.tvTitle.text = it.title
            binding.tvTime.text = "Tiempo: ${it.time}"
            binding.tvIngredients.text = it.ingredients
            Glide.with(this).load(it.imageUrl).into(binding.imgRecipe)

            // Configura botón de YouTube
            binding.btnYoutube.setOnClickListener {
                if (!recipe.youtubeUrl.isNullOrEmpty()) {
                    val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.youtubeUrl))
                    startActivity(youtubeIntent)
                } else {
                    Toast.makeText(this, "URL de YouTube no disponible", Toast.LENGTH_SHORT).show()
                }
            }

            // Configura botón de favoritos
            updateFavoriteButton(it)
            binding.btnFavorite.setOnClickListener {
                recipe?.let { currentRecipe ->
                    if (FavoritesManager.isFavorite(currentRecipe)) {
                        FavoritesManager.removeFromFavorites(currentRecipe)
                        Toast.makeText(this, "Removido de favoritos", Toast.LENGTH_SHORT).show()
                    } else {
                        FavoritesManager.addToFavorites(currentRecipe)
                        Toast.makeText(this, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
                    }
                    updateFavoriteButton(currentRecipe)
                }
            }

        } ?: run {
            // Si no hay datos, muestra un mensaje y cierra la actividad
            Toast.makeText(this, "No se pudieron cargar los datos", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateFavoriteButton(recipe: RecipeModel) {
        if (FavoritesManager.isFavorite(recipe)) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite) // Ícono lleno
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_fav_unfill) // Ícono vacío
        }
    }
}
