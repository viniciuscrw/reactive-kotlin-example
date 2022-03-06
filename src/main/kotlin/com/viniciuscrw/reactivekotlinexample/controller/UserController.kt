package com.viniciuscrw.reactivekotlinexample.controller

import com.viniciuscrw.reactivekotlinexample.dto.PagingResponseDTO
import com.viniciuscrw.reactivekotlinexample.dto.UserCreateRequestDTO
import com.viniciuscrw.reactivekotlinexample.dto.UserResponseDTO
import com.viniciuscrw.reactivekotlinexample.dto.UserUpdateRequestDTO
import com.viniciuscrw.reactivekotlinexample.dto.toResponseDTO
import com.viniciuscrw.reactivekotlinexample.exception.UserAlreadyExistsException
import com.viniciuscrw.reactivekotlinexample.exception.UserNotFoundException
import com.viniciuscrw.reactivekotlinexample.repository.UserRepository
import java.util.UUID
import javax.validation.Valid
import kotlin.math.ceil
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users", produces = [APPLICATION_JSON_VALUE])
class UserController(
    private val userRepository: UserRepository
) {
    @PostMapping
    suspend fun createUser(
        @RequestBody @Valid userCreateRequestDTO: UserCreateRequestDTO
    ): UserResponseDTO {
        userRepository.findByEmail(userCreateRequestDTO.email)
            .awaitSingleOrNull()?.let {
                throw UserAlreadyExistsException("User with e-mail ${userCreateRequestDTO.email} already exists.")
            }

        return userRepository.save(userCreateRequestDTO.toModel().asNew())
            .awaitSingle()
            .toResponseDTO()
    }

    @GetMapping
    suspend fun listUsers(
        @RequestParam pageNumber: Int = 1,
        @RequestParam pageSize: Int = 10
    ): PagingResponseDTO<UserResponseDTO> {
        val offset = (pageSize * pageNumber) - pageSize
        val usersList = userRepository.findAllUsers(limit = pageSize, offset = offset)
            .collectList()
            .awaitFirst()
            .map { it.toResponseDTO() }

        val totalUsers = userRepository.count().awaitFirst()

        return PagingResponseDTO(
            items = usersList,
            totalPages = ceil(totalUsers.toDouble() / pageSize.toDouble()).toLong()
        )
    }

    @PatchMapping("/{userId}")
    suspend fun updateUser(
        @PathVariable userId: UUID,
        @RequestBody userUpdateRequestDTO: UserUpdateRequestDTO
    ): UserResponseDTO {
        val user = userRepository.findById(userId).awaitFirstOrElse {
            throw UserNotFoundException("User with id $userId not found.")
        }

        userUpdateRequestDTO.email?.let {
            userRepository.findByEmail(it).awaitSingleOrNull()
                ?.let { existingUser ->
                    if (existingUser.id != userId)
                        throw UserAlreadyExistsException("Another user with e-mail ${existingUser.email} already exists.")
                }
        }

        return userRepository.save(userUpdateRequestDTO.toModel(user))
            .awaitSingle()
            .toResponseDTO()
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    suspend fun deleteUser(
        @PathVariable userId: UUID
    ) {
        val user = userRepository.findById(userId).awaitFirstOrElse {
            throw UserNotFoundException("User with id $userId not found.")
        }

        userRepository.delete(user).subscribe()
    }
}