package com.illium.illiumbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.illium.illiumbackend"])
@EntityScan(basePackages = ["com.illium.illiumbackend.model"])
class IlliumbackendApplication

fun main(args: Array<String>) {
	runApplication<IlliumbackendApplication>(*args)
}
