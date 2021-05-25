package com.antonvovk.ThyroidNoduleDMS.security.services

import com.antonvovk.ThyroidNoduleDMS.api.dto.AuthenticationDto
import com.antonvovk.ThyroidNoduleDMS.api.dto.JwtTokenDto

interface AuthenticationService {
    fun authenticate(authenticationDto: AuthenticationDto): JwtTokenDto
}
