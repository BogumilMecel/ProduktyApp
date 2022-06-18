package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Tax(
    val code: String,
    val id: Int,
    val name: String,
    val rate: Double
)