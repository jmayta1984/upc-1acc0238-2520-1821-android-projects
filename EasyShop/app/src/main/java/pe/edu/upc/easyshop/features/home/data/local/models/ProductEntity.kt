package pe.edu.upc.easyshop.features.home.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Double
)
