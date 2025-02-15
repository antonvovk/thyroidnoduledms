package com.antonvovk.thyroidnodule.api.controller

import com.antonvovk.thyroidnodule.api.dto.*
import com.antonvovk.thyroidnodule.db.users.mappers.UserMapper
import com.antonvovk.thyroidnodule.security.services.AuthenticationService
import com.antonvovk.thyroidnodule.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/authentication")
@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val userMapper: UserMapper,
    private val userService: UserService
) {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun createAuthenticationToken(@RequestBody authenticationDto: AuthenticationDto): JwtTokenDto {
        return authenticationService.authenticate(authenticationDto)
    }

    @PostMapping("/register")
    fun register(@RequestBody body: RegistrationDto) {
        val user = userMapper.map(body)
        userService.register(user)
    }

    @PutMapping
    fun updateInfo(@RequestBody body: UpdateUserInfoDto): UserDto {
        return userMapper.map(userService.update(body))
    }
}
