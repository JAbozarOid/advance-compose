package com.example.advancecompose.model

import java.io.Serializable

/**
 * the Serializable Interface. Which Is required
 * when we are using Intent to transform a TvShow object from one activity to another
 */
data class TvShow(
    var id: Int = 0,
    var name: String = "",
    var year: Int = 0,
    var rate: Double = 0.0,
    var imageId: Int = 0,
    var overview: String = ""
) : Serializable
