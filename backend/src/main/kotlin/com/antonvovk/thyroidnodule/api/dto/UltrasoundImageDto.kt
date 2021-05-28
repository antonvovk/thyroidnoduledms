package com.antonvovk.thyroidnodule.api.dto

data class UltrasoundImageDto(
    val id: Long,
    val filename: String,
    var height: Int,
    var width: Int
)
