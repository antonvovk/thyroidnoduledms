package com.antonvovk.thyroidnodule.security.services.impl

import com.antonvovk.thyroidnodule.api.dto.AuthenticationDto
import com.antonvovk.thyroidnodule.api.dto.JwtTokenDto
import com.antonvovk.thyroidnodule.api.exceptions.common.EntityNotFoundException
import com.antonvovk.thyroidnodule.db.users.models.User
import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.security.exception.BadCredentialsException
import com.antonvovk.thyroidnodule.security.services.AuthenticationService
import com.antonvovk.thyroidnodule.security.utils.JwtTokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl @Autowired constructor(
    private val jwtTokenUtils: JwtTokenUtils,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override fun authenticate(authenticationDto: AuthenticationDto): JwtTokenDto {
        val user = userRepository.findByEmail(authenticationDto.username)
            ?: throw EntityNotFoundException(authenticationDto.username, User::class)

        if (!passwordEncoder.matches(authenticationDto.password, user.passwordHash)) {
            throw BadCredentialsException("Invalid password")
        }

        return JwtTokenDto(
            jwtTokenUtils.generateToken(authenticationDto.username)
        )
    }
}
