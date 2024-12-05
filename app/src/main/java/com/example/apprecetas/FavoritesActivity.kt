package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecetas.adapters.RecipeSubAdapter

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val favorites = FavoritesManager.getFavorites()

        if (favorites.isEmpty()) {
            Toast.makeText(this, "No tienes recetas favoritas", Toast.LENGTH_SHORT).show()
        }

        val adapter = RecipeSubAdapter(favorites) { recipe ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.rvFavorites).apply {
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            this.adapter = adapter
        }
    }

}