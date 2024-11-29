package com.example.apprecetas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apprecetas.dao.RecipeDao
import com.example.apprecetas.entities.Category
import com.example.apprecetas.entities.CategoryItems
import com.example.apprecetas.entities.Recipes
import com.example.apprecetas.entities.converter.CategoryListConverter


@Database(entities = [Recipes::class,CategoryItems::class,Category::class,CategoryListConverter::class],version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
    companion object{

        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if (recipesDatabase != null){
                recipesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"

                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}