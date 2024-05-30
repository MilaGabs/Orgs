package com.example.orgs.model

import java.math.BigDecimal

// Data class carrega algumas propriedades para a classe, por exemplo o toString()
data class Product(
    val name: String,
    val description: String,
    val value: BigDecimal, // Similar to double but with a big precision
    val image: String? = null
)
