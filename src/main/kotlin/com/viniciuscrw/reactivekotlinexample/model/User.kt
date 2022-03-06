package com.viniciuscrw.reactivekotlinexample.model

import java.util.UUID
import java.util.UUID.randomUUID
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table("user")
class User(
    @Id
    private val id: UUID? = randomUUID(),

    val firstName: String,

    val lastName: String,

    val email: String

) : Persistable<UUID> {

    @Transient
    private var newUser: Boolean = false

    override fun getId() = id

    override fun isNew() = newUser

    fun asNew(): User {
        newUser = true
        return this
    }
}
