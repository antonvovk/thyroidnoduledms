package com.antonvovk.thyroidnodule.api.exceptions.handler

import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

class ApiValidationError(
    val objectName: String,
    val message: String?,
    val field: String? = null,
    val rejectedValue: Any? = null
) : ApiSubError

fun FieldError.toApiValidationError() = ApiValidationError(objectName, defaultMessage, field, rejectedValue)

fun ObjectError.toApiValidationError() = ApiValidationError(objectName, defaultMessage)

