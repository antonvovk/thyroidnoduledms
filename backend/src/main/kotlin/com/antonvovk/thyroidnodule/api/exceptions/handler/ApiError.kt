package com.antonvovk.thyroidnodule.api.exceptions.handler

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import java.time.LocalDateTime

data class ApiError(
    val status: HttpStatus,
    val message: String,
    val debugMessage: String? = null
) {

    constructor(status: HttpStatus, message: String, ex: Throwable) : this(status, message, ex.localizedMessage)

    private val _subErrors = mutableListOf<ApiSubError>()

    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now()

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val subErrors: List<ApiSubError>
        get() = _subErrors

    fun addValidationErrors(fieldErrors: List<FieldError>) =
        _subErrors.addAll(fieldErrors.map(FieldError::toApiValidationError))

    fun addValidationObjectErrors(globalErrors: List<ObjectError>) =
        _subErrors.addAll(globalErrors.map(ObjectError::toApiValidationError))
}

interface ApiSubError
