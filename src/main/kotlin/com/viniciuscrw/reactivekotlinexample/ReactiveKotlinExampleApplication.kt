package com.viniciuscrw.reactivekotlinexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories
@SpringBootApplication
class ReactiveKotlinExampleApplication

fun main(args: Array<String>) {
	runApplication<ReactiveKotlinExampleApplication>(*args)
}
