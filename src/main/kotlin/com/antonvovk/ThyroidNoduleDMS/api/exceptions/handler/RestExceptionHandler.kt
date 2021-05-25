package com.antonvovk.ThyroidNoduleDMS.api.exceptions.handler

import com.antonvovk.ThyroidNoduleDMS.api.exceptions.common.EntityNotFoundException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.hibernate.exception.ConstraintViolationException as DbConstraintViolationException

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler(
    private val messageSource: MessageSource
) : ResponseEntityExceptionHandler() {
    /**
     * Triggered when a 'required' request parameter is missing
     */
    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val msg = getMessage("error.request.missing-parameter", arrayOf(ex.parameterName))
        return ApiError(HttpStatus.BAD_REQUEST, msg, ex).toResponse()
    }

    /**
     * This one triggers when Media type not supported or JSON is invalid.
     */
    override fun handleHttpMediaTypeNotSupported(
        ex: HttpMediaTypeNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val msg = getMessage(
            "error.request.media-type.unsupported",
            arrayOf(ex.contentType.toString(), ex.supportedMediaTypes.joinToString())
        )
        return ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, msg, ex).toResponse()
    }

    /**
     * Triggered when an object fails @Valid validation.
     */
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val msg = getMessage("error.validation")
        return ApiError(HttpStatus.BAD_REQUEST, msg).apply {
            addValidationErrors(ex.bindingResult.fieldErrors)
            addValidationObjectErrors(ex.bindingResult.globalErrors)
        }.toResponse()
    }

    /**
     * Triggered when request JSON is malformed.
     */
    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val msg = getMessage("error.request.json.malformed")
        return ApiError(HttpStatus.BAD_REQUEST, msg, ex).toResponse()
    }

    /**
     * Handle HttpMessageNotWritableException.
     */
    override fun handleHttpMessageNotWritable(
        ex: HttpMessageNotWritableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val msg = getMessage("error.response.write-json")
        return ApiError(HttpStatus.INTERNAL_SERVER_ERROR, msg, ex).toResponse()
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     */
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(
        ex: DataIntegrityViolationException,
        request: WebRequest,
    ): ResponseEntity<Any> {
        if (ex.cause is DbConstraintViolationException) {
            return ApiError(
                HttpStatus.CONFLICT,
                getMessage("error.database.conflict"),
                (ex.cause as DbConstraintViolationException).sqlException
            ).toResponse()
        }

        return ApiError(HttpStatus.INTERNAL_SERVER_ERROR, getMessage("error.internal"), ex).toResponse()
    }

    /**
     * Triggered when JpaRepository can't find single entity marked as nonnull
     */
    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException): ResponseEntity<Any> {
        // Parameters are not meaningful, just for substitution
        val msg = getMessage("error.database.not-found", arrayOf("Entity", ""))
        return ApiError(HttpStatus.NOT_FOUND, msg, ex).toResponse()
    }

    /**
     * When argument with invalid type passed to a controller's method
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(
        ex: MethodArgumentTypeMismatchException,
        request: WebRequest
    ): ResponseEntity<Any> {
        val typeName = ex.requiredType?.simpleName ?: ""
        val msg = getMessage(
            "error.request.mismatch-argument",
            arrayOf(ex.name, ex.value ?: "", typeName)
        )
        return ApiError(HttpStatus.BAD_REQUEST, msg, ex).toResponse()
    }

    /**
     * Handles IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<Any> {
        val msg = getMessage("error.illegal-argument")
        return ApiError(HttpStatus.BAD_REQUEST, msg, ex).toResponse()
    }

    /**
     * Handles EntityNotFoundException
     *
     * @param ex EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<Any> {
        val msg = getMessage("error.database.not-found", arrayOf(ex.name, ex.id))
        return ApiError(HttpStatus.NOT_FOUND, msg, ex).toResponse()
    }

    private fun ApiError.toResponse() = ResponseEntity<Any>(this, this.status)

    private fun getMessage(code: String, args: Array<Any>? = null) =
        messageSource.getMessage(code, args, LocaleContextHolder.getLocale())
}
