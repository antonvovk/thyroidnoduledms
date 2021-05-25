package com.antonvovk.thyroidnodule.security.services

import com.antonvovk.thyroidnodule.api.dto.AuthenticationDto
import com.antonvovk.thyroidnodule.api.dto.JwtTokenDto

interface AuthenticationService {
    fun authenticate(authenticationDto: AuthenticationDto): JwtTokenDto
}
