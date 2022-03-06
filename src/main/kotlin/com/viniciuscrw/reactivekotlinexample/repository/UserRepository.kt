package com.viniciuscrw.reactivekotlinexample.repository

import com.viniciuscrw.reactivekotlinexample.model.User
import java.util.UUID
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository : R2dbcRepository<User, UUID> {
    fun findByEmail(email: String): Mono<User>

    @Query("SELECT * FROM user LIMIT :limit OFFSET :offset")
    fun findAllUsers(limit: Int, offset: Int): Flux<User>
}