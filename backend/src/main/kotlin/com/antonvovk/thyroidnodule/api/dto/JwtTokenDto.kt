package com.antonvovk.thyroidnodule.api.dto

data class JwtTokenDto(
    val token: String,
    val user: UserDto
)
