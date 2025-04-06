package com.creospace.recipe_kmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Cats (
    val id: String? = "",
    val url: String? = "",
    val breeds: List<Breed> = emptyList(),
    val width: Int,
    val height: Int
)

@Serializable
data class Breed(
    val name: String? = "",
    val temperament: String? = "",
    val description: String? = ""
)