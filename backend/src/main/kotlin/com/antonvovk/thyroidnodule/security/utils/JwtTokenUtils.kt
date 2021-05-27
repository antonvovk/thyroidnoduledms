package com.antonvovk.thyroidnodule.security.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtils {

    @Value("\${jwt.validity}")
    private val validity: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null
    fun getUsernameFromToken(token: String?): String {
        val claims: Claims = getAllClaimsFromToken(token)
        return claims.subject
    }

    fun getExpirationDateFromToken(token: String?): Date {
        val claims: Claims = getAllClaimsFromToken(token)
        return claims.expiration
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser()
            .setSigningKey(Base64.getEncoder().encode(secret!!.toByteArray()))
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String?): Boolean {
        val expiration: Date = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(email: String): String {
        return doGenerateToken(email)
    }

    private fun doGenerateToken(subject: String): String {
        return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + validity!!))
            .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret!!.toByteArray()))
            .compact()
    }

    fun validateToken(token: String?, email: String): Boolean {
        val username = getUsernameFromToken(token)
        return username == email && !isTokenExpired(token)
    }
}
