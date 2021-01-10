package com.example.learningkotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionHandle {
    @ExceptionHandler(
        NotFoundException::class
    )
    fun handleNotFoundException(
        exception: NotFoundException,
        webRequest: HttpServletResponse
    ) {
        webRequest.sendError(HttpStatus.BAD_REQUEST.value(), exception.message)
    }

    @ExceptionHandler(
        Exception::class
    )
    fun handleException(
        exception: Exception,
        webRequest: HttpServletResponse
    ) {
        webRequest.sendError(HttpStatus.BAD_REQUEST.value(), exception.message)
    }
}