package com.antonvovk.thyroidnodule.api.dto

data class UpdateUserInfoDto(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val workPlace: String,
    val email: String,
    val oldPassword: String,
    val newPassword: String
)
