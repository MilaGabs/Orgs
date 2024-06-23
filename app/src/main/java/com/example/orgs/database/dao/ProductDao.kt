package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orgs.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    // O Flow implementa o conceito de Observer, quando tiver alguma alteração na lista de produtos ele avisará quem esta escutando as publicações dele
    @Query("SELECT * FROM Product")
    fun searchAll() : Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :id")
    suspend fun searchById(id: Int) : Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg product: Product)

    @Delete
    suspend fun delete(vararg product: Product)
}
