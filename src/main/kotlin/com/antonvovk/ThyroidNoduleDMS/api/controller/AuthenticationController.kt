package com.antonvovk.ThyroidNoduleDMS.api.controller

import com.antonvovk.ThyroidNoduleDMS.api.dto.AuthenticationDto
import com.antonvovk.ThyroidNoduleDMS.api.dto.JwtTokenDto
import com.antonvovk.ThyroidNoduleDMS.security.services.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/authentication")
@RestController
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    fun createAuthenticationToken(@RequestBody authenticationDto: AuthenticationDto): JwtTokenDto {
        return authenticationService.authenticate(authenticationDto)
    }
}
