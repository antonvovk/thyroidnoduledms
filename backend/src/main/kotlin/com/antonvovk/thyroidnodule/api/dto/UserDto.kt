package com.antonvovk.thyroidnodule.api.dto

data class UserDto(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val workPlace: String,
    val email: String,
    val qualificationTestPassed: Boolean? = null,
    val permissions: List<String>? = null
)
