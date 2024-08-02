package com.example.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.orgs.database.dao.ProductDao
import com.example.orgs.database.dao.UserDao
import com.example.orgs.model.Product
import com.example.orgs.model.User

@Database(entities = [Product::class, User::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao

    companion object {
        // Criando o conceito de SINGLETON
        // Volatitle garante que a mesma instancia do banco sera utilizada por todas as threads
        // Sem ela pode acontecer de serem criadas instancias diferentes para duas threads diferentes executadas ao mesmo tempo
        @Volatile
        private var db: AppDatabase? = null
        fun instance(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            )
                .addMigrations(MIGRATION_1_2)
                .build()
                .also {
                    db = it
                }
        }
    }
}
