package com.example.learningkotlin.exception

import javassist.NotFoundException
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
        exception: Exception,
        webRequest: HttpServletResponse
    ) {
        webRequest.sendError(HttpStatus.BAD_REQUEST.value(), exception.message)
    }
}