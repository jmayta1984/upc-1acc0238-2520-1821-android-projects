package pe.edu.upc.easyshop.features.home.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.easyshop.features.home.data.local.dao.ProductDao
import pe.edu.upc.easyshop.features.home.data.local.models.ProductEntity
import pe.edu.upc.easyshop.features.home.data.remote.services.ProductService
import pe.edu.upc.easyshop.features.home.domain.repositories.ProductRepository
import pe.edu.upc.easyshop.shared.models.Product

class ProductRepositoryImpl(
    private val service: ProductService,
    private val dao: ProductDao
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {

        val response = service.getAllProducts()

        if (response.isSuccessful) {
            response.body()?.let { productsWrapperDto ->
                productsWrapperDto.products?.let { productsDto ->
                    return@withContext productsDto.map { dto ->
                        Product(
                            id = dto.id ?: 0,
                            name = dto.title ?: "",
                            price = dto.price ?: 0.0,
                            description = dto.description ?: "",
                            image = dto.thumbnail ?: ""
                        )
                    }
                }

            }
        }

        return@withContext emptyList()
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