package com.illium.illiumbackend.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PreflightController {

    @RequestMapping(method = [RequestMethod.OPTIONS], path = ["/**"])
    fun handlePreflight(): ResponseEntity<Void> {
        val headers = HttpHeaders()
        headers.add("Access-Control-Allow-Origin", "*") // Or your specific origin
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization")
        headers.add("Access-Control-Max-Age", "3600") // Cache preflight response for 1 hour
        return ResponseEntity(headers, HttpStatus.OK)
    }
}
