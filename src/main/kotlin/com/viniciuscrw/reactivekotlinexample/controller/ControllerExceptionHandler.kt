package com.viniciuscrw.reactivekotlinexample.controller

import com.viniciuscrw.reactivekotlinexample.exception.UserAlreadyExistsException
import com.viniciuscrw.reactivekotlinexample.exception.UserNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(exception: UserAlreadyExistsException) =
        ResponseEntity.badRequest().body(exception.message)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(exception: UserNotFoundException) =
        ResponseEntity.notFound().build<Any>()
}