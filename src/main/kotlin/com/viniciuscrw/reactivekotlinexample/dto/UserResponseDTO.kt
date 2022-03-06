package com.viniciuscrw.reactivekotlinexample.dto

import com.viniciuscrw.reactivekotlinexample.model.User
import java.util.UUID

data class UserResponseDTO(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun User.toResponseDTO() = UserResponseDTO(
    id = id!!,
    firstName = firstName,
    lastName = lastName,
    email = email
)
