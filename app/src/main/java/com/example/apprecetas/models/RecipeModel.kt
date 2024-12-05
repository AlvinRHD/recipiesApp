package com.example.apprecetas.models

data class RecipeModel (
    var id: String = "",
    var title: String = "",
    var imageUrl: String = "",
    var category: String = "",
    var ingredients: List<String> = listOf(),
    var instructions: List<String> = listOf(),
    var time: Int = 0,
    var calories: Int = 0,
    var servings: Int = 0
)