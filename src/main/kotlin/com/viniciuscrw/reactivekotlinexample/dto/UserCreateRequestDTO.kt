package com.viniciuscrw.reactivekotlinexample.dto

import com.viniciuscrw.reactivekotlinexample.model.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UserCreateRequestDTO(
    @field:NotEmpty
    val firstName: String,

    @field:NotEmpty
    val lastName: String,

    @field:Email
    val email: String
) {
    fun toModel() = User(
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}
