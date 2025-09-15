package pe.edu.upc.easyshop.features.home.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getProducts(): Response<ProductsWrapperDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductDto>
}