package com.antonvovk.thyroidnodule.security

import com.antonvovk.thyroidnodule.db.users.repositories.UserRepository
import com.antonvovk.thyroidnodule.security.exception.BadCredentialsException
import com.antonvovk.thyroidnodule.security.utils.JwtTokenUtils
import com.antonvovk.thyroidnodule.utils.Logging
import com.antonvovk.thyroidnodule.utils.logger
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(private val jwtTokenUtilsUtil: JwtTokenUtils, private val userRepository: UserRepository) :
    OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain
    ) {
        val requestTokenHeader = request.getHeader("Authorization")
        var username: String? = null
        var jwtToken: String? = null
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)
            try {
                username = jwtTokenUtilsUtil.getUsernameFromToken(jwtToken)
            } catch (e: IllegalArgumentException) {
                log.warn("Unable to get JWT Token")
            } catch (e: ExpiredJwtException) {
                log.warn("JWT Token has expired")
            }
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            if (userRepository.findByEmail(username) == null) {
                throw  BadCredentialsException(
                    String.format("User with username %s does not exists", username)
                )
            }
            if (jwtTokenUtilsUtil.validateToken(jwtToken, username)) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(username, null, Collections.emptyList())
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        chain.doFilter(request, response)
    }

    companion object : Logging {
        private val log = logger()
    }
}

