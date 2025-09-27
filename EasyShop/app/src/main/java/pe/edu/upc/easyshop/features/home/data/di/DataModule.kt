package pe.edu.upc.easyshop.features.home.data.di

import androidx.room.Room
import pe.edu.upc.easyshop.MyApplication
import pe.edu.upc.easyshop.features.home.data.local.dao.ProductDao
import pe.edu.upc.easyshop.features.home.data.local.database.AppDatabase
import pe.edu.upc.easyshop.features.home.data.remote.services.ProductService
import pe.edu.upc.easyshop.features.home.data.repositories.ProductRepositoryImpl
import pe.edu.upc.easyshop.features.home.domain.repositories.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun getProductRepository(): ProductRepository {
        return ProductRepositoryImpl(getProductService(), getProductDao())
    }


    fun getProductDao(): ProductDao {
        return getAppDatabase().productDao()
    }

    fun getAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context = MyApplication.INSTANCE.applicationContext,
            klass = AppDatabase::class.java,
            name = "easyshop"
        )
            .build()
    }

    fun getProductService(): ProductService {
        return getRetrofit().create(ProductService::class.java)
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}