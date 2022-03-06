package com.viniciuscrw.reactivekotlinexample.dto

data class PagingResponseDTO<T>(
    val items: List<T>,
    val totalPages: Long
)
