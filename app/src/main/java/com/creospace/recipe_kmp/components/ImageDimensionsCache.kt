package com.creospace.recipe_kmp.components

object ImageDimensionsCache {
    private val dimensionMap = mutableMapOf<String, Float>()

    fun getAspectRatio(url: String): Float? = dimensionMap[url]

    fun setAspectRatio(url: String, aspectRatio: Float) {
        dimensionMap[url] = aspectRatio
    }
}