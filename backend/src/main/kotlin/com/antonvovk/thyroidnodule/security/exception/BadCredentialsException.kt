package com.antonvovk.thyroidnodule.security.exception

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class BadCredentialsException(message: String?) : AuthenticationException(message)

