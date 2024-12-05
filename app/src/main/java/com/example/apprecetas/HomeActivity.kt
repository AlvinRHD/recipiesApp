package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprecetas.adapters.RecipeAdapter
import com.example.apprecetas.adapters.RecipeSubAdapter
import com.example.apprecetas.databinding.ActivityHomeBinding
import com.example.apprecetas.models.RecipeModel

class HomeActivity : AppCompatActivity() {

    private lateinit var categoryAdapter: RecipeAdapter
    private lateinit var binding: ActivityHomeBinding
    private val recipeList = mutableListOf<RecipeModel>() // Lista local de recetas
    private val categoryList = mutableListOf<String>() // Lista de categorías únicas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Configurar RecyclerView de categorías
        categoryAdapter = RecipeAdapter(categoryList) { selectedCategory ->
            showRecipesByCategory(selectedCategory) // Muestra las recetas de la categoría seleccionada
        }

        binding.rvMainCategory.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        // Configurar RecyclerView para recetas filtradas
        binding.rvSubCategory.layoutManager = LinearLayoutManager(this)

        // Cargar datos locales
        loadLocalRecipes()
    }

    private fun loadLocalRecipes() {
        // Agregar recetas de ejemplo
        recipeList.add(
            RecipeModel(
                category = "Postres",
                id = "1",
                imageUrl = "https://www.recetasnestle.com.mx/sites/default/files/styles/cropped_recipe_card_new/public/srh_recipes/54c6cbcbc6d611e056122d64560cd9c1.jpg.webp?itok=hemI3A_x",
                ingredients = "Harina, azúcar, huevos",
                time = 30,
                title = "Tarta de Chocolate",
                youtubeUrl = "https://youtu.be/VieEP4sCxLs?si=oZfSMRjaPjppciU3"
            )
        )

        recipeList.add(
            RecipeModel(
                category = "Plato Fuerte",
                id = "2",
                imageUrl = "https://www.recetasnestle.com.mx/sites/default/files/styles/cropped_recipe_card_new/public/srh_recipes/54c6cbcbc6d611e056122d64560cd9c1.jpg.webp?itok=hemI3A_x",
                ingredients = "Carne, especias, vegetales",
                time = 45,
                title = "Asado de Res",
                youtubeUrl = "https://youtu.be/VieEP4sCxLs?si=oZfSMRjaPjppciU3"
            )
        )

        recipeList.add(
            RecipeModel(
                category = "Postres",
                id = "3",
                imageUrl = "https://www.recetasnestle.com.mx/sites/default/files/styles/cropped_recipe_card_new/public/srh_recipes/54c6cbcbc6d611e056122d64560cd9c1.jpg.webp?itok=hemI3A_x",
                ingredients = "Harina, azúcar, huevos",
                time = 30,
                title = "Tarta de Chocolate",
                youtubeUrl = "https://youtu.be/VieEP4sCxLs?si=oZfSMRjaPjppciU3"
            )
        )

        recipeList.add(
            RecipeModel(
                category = "Plato Fuerte",
                id = "4",
                imageUrl = "https://www.recetasnestle.com.mx/sites/default/files/styles/cropped_recipe_card_new/public/srh_recipes/54c6cbcbc6d611e056122d64560cd9c1.jpg.webp?itok=hemI3A_x",
                ingredients = "Carne, especias, vegetales",
                time = 45,
                title = "Asado de Res",
                youtubeUrl = "https://youtu.be/VieEP4sCxLs?si=oZfSMRjaPjppciU3"
            )
        )

        recipeList.add(
            RecipeModel(
                category = "Acompañamiento",
                id = "5",
                imageUrl = "https://i.blogs.es/59413d/guarnicion-asados/650_1200.jpg",
                ingredients = "Patata, zanahoria, cebolla",
                time = 30,
                title = "Guarnición",
                youtubeUrl = "https://www.youtube.com/watch?v=Vx8quROGkPk"
            )
        )


        // Extraer categorías únicas
        categoryList.clear()
        categoryList.addAll(recipeList.map { it.category }.distinct())

        // Notifica al adaptador de categorías
        categoryAdapter.notifyDataSetChanged()

        // Mensaje si no hay recetas
        if (recipeList.isEmpty()) {
            Toast.makeText(this, "No se encontraron recetas.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRecipesByCategory(category: String) {
        // Filtra las recetas por la categoría seleccionada
        val filteredRecipes = recipeList.filter { it.category == category }

        // Configura el adaptador de subcategorías para mostrar las recetas filtradas
        val subAdapter = RecipeSubAdapter(filteredRecipes) { recipe ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }

        binding.rvSubCategory.adapter = subAdapter
    }

    // Método para cerrar sesión
    private fun logoutUser() {
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                // Redirigir a la actividad de favoritos
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                logoutUser()  // Llama al método para cerrar sesión
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
