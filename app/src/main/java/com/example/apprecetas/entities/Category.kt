package com.example.apprecetas.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.apprecetas.entities.converter.CategoryListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CategoryItems")
data class Category(

    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "categorieitems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryListConverter::class)
    val categorieitems: List<CategoryItems>? = null
)

