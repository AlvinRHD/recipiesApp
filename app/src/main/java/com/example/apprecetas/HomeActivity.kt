package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprecetas.adapters.RecipeAdapter
import com.example.apprecetas.databinding.ActivityHomeBinding
import com.example.apprecetas.models.RecipeModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firestore: FirebaseFirestore
    private val recipeList = mutableListOf<RecipeModel>()
    private lateinit var mainAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)

        firestore = FirebaseFirestore.getInstance()

        // Configurar RecyclerView
        mainAdapter = RecipeAdapter(recipeList) { recipe ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.id)
            startActivity(intent)
        }

        binding.rvMainCategory.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mainAdapter
        }

        // Cargar datos de Firebase
        loadRecipes()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                // Navegar a Favoritos
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                // Cerrar Sesión
                logoutUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadRecipes() {
        firestore.collection("recipes")
            .get()
            .addOnSuccessListener { documents ->
                recipeList.clear()
                for (doc in documents) {
                    Log.d("Firestore", "Documento ID: ${doc.id}, Datos: ${doc.data}")
                    val recipe = doc.toObject(RecipeModel::class.java).apply {
                        id = doc.id
                    }
                    recipeList.add(recipe)
                }
                mainAdapter.notifyDataSetChanged()
                if (recipeList.isEmpty()) {
                    Log.d("Firestore", "No hay recetas disponibles en la lista.")
                }
            }
            .addOnFailureListener {
                Log.e("Firestore", "Error al cargar recetas", it)
            }

    }

    // Método para cerrar sesión
    private fun logoutUser() {
        // Agregar lógica para cerrar sesión (Firebase o SharedPreferences)
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}
