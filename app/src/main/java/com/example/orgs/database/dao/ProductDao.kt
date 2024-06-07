package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.orgs.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun searchAll() : List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun searchById(id: Int) : Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg product: Product)

    @Delete
    fun delete(vararg product: Product)
}