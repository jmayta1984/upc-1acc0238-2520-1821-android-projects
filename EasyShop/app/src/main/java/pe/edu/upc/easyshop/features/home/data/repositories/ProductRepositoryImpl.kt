package pe.edu.upc.easyshop.features.home.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.easyshop.features.home.data.remote.services.ProductService
import pe.edu.upc.easyshop.features.home.domain.repositories.ProductRepository
import pe.edu.upc.easyshop.shared.models.Product

class ProductRepositoryImpl(private val service: ProductService): ProductRepository {
    override suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {

        val response = service.getAllProducts()

        if (response.isSuccessful) {
            response.body()?.let { productsWrapperDto ->
                productsWrapperDto.products?.let { productsDto ->
                    return@withContext productsDto.map { dto ->
                        Product(name = dto.title ?: "",
                            price = dto.price ?: 0.0,
                            description = dto.description ?: "",
                            image = dto.thumbnail ?: "")
                    }
                }

            }
        }

        return@withContext emptyList()
    }
}