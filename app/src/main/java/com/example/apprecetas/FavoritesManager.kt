package com.example.apprecetas

import com.example.apprecetas.models.RecipeModel

object FavoritesManager {
    private val favoriteRecipes = mutableListOf<RecipeModel>()

    fun addToFavorites(recipe: RecipeModel) {
        if (!favoriteRecipes.contains(recipe)) {
            favoriteRecipes.add(recipe)
        }
    }

    fun removeFromFavorites(recipe: RecipeModel) {
        favoriteRecipes.remove(recipe)
    }

    fun isFavorite(recipe: RecipeModel): Boolean {
        return favoriteRecipes.contains(recipe)
    }

    fun getFavorites(): List<RecipeModel> {
        return favoriteRecipes
    }
}