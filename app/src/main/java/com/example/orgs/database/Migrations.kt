package com.example.orgs.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
                CREATE TABLE IF NOT EXISTS `User` (
                    `id` TEXT NOT NULL,
                    `nome` TEXT NOT NULL,
                    `senha` TEXT NOT NULL,
                    PRIMARY KEY(`id`)
                )
            """.trimIndent()
        )
    }
}