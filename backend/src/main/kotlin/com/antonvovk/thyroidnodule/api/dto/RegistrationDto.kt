package com.antonvovk.thyroidnodule.api.dto

data class RegistrationDto(
    val user: UserDto,
    val passwordHash: String
)
