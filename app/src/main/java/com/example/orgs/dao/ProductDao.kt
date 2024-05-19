package com.example.orgs.dao

import com.example.orgs.model.Product

class ProductDao {

    fun add(product: Product) {
        products.add(product)
    }

    fun getAll() : List<Product> {
        // Aqui utilizamos o to list para criar uma nova lista em um novo end de memoria,
        // pq se só retornarmos a lista quando ela for manipulada por quem a recebeu
        // vai estar manipulando a lista que esta no DAO
        return products.toList()
    }

    // companion mantem uma instancia da lista ativa de maneira estatica
    companion object {
        private val products = mutableListOf<Product>()
    }
}
