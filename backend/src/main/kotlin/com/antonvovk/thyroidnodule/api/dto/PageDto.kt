package com.antonvovk.thyroidnodule.api.dto

data class PageDto<T>(
    val elements: List<T>,
    val size: Long
)
