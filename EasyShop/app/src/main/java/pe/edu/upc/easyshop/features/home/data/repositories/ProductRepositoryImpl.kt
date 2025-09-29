package pe.edu.upc.easyshop.features.home.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.easyshop.features.home.data.local.dao.ProductDao
import pe.edu.upc.easyshop.features.home.data.local.models.ProductEntity
import pe.edu.upc.easyshop.features.home.data.remote.services.ProductService
import pe.edu.upc.easyshop.features.home.domain.repositories.ProductRepository
import pe.edu.upc.easyshop.shared.models.Product
import javax.inject.Inject
import kotlin.collections.isNotEmpty

class ProductRepositoryImpl @Inject constructor (
    private val service: ProductService,
    private val dao: ProductDao
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {

        val response = service.getAllProducts()
        if (response.isSuccessful) {
            Log.d("ProductRepositoryImpl", response.message())
            response.body()?.let { productsWrapperDto ->
                productsWrapperDto.products?.let { productsDto ->
                    return@withContext productsDto.map { dto ->
                        Product(
                            id = dto.id ?: 0,
                            name = dto.title ?: "",
                            price = dto.price ?: 0.0,
                            description = dto.description ?: "",
                            image = dto.thumbnail ?: "",
                            isFavorite = dao.fetchProductById(dto.id ?: 0).isNotEmpty()
                        )
                    }
                }

            }
        }

        return@withContext emptyList()
    }

    override suspend fun getProductById(id: Int): Product? = withContext(Dispatchers.IO) {

        val response = service.getProductById(id)

        if (response.isSuccessful) {
            response.body()?.let { productDto ->
                val isFavorite = dao.fetchProductById(productDto.id ?: 0).isNotEmpty()
                return@withContext Product(
                    id = productDto.id ?: 0,
                    name = productDto.title ?: "",
                    price = productDto.price ?: 0.0,
                    description = productDto.description ?: "",
                    image = productDto.thumbnail ?: "",
                    isFavorite = isFavorite
                )
            }
        }
        return@withContext null

    }

    override suspend fun saveProduct(product: Product) = withContext(Dispatchers.IO) {
        dao.insert(
            ProductEntity(
                id = product.id,
                name = product.name,
                image = product.image,
                description = product.description,
                price = product.price
            )
        )

    }

    override suspend fun removeProduct(product: Product) = withContext(Dispatchers.IO) {
        dao.delete(
            ProductEntity(
                id = product.id,
                name = product.name,
                image = product.image,
                description = product.description,
                price = product.price
            )
        )
    }
}