package com.viniciuscrw.reactivekotlinexample.dto

import com.viniciuscrw.reactivekotlinexample.model.User
import javax.validation.constraints.Email

data class UserUpdateRequestDTO(
    val firstName: String? = null,

    val lastName: String? = null,

    @field:Email
    val email: String? = null,
) {
    fun toModel(userRef: User) = User(
        id = userRef.id,
        firstName = firstName ?: userRef.firstName,
        lastName = lastName ?: userRef.lastName,
        email = email ?: userRef.email
    )
}
