package com.illium.illiumbackend.model

data class ApiResponse<T>(
    val status: String,      // e.g., "success" or "error"
    val message: String,     // A short message describing the outcome
    val data: T? = null,     // The payload (generic type for flexibility)
    val details: String? = null // Additional details (useful for errors)
)
