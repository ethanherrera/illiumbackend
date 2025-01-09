package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    val userId: Long = 0L,

    @Column(name = "email", nullable = false, length = 255)
    var email: String,

    @Column(name = "password_hash", nullable = false, length = 255)
    var passwordHash: String,

    @Column(name = "username", nullable = false, length = 255)
    var username: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}

// Request DTO for User
data class UserRequest(
    val email: String,
    val passwordHash: String,
    val username: String
)

// Response DTO for User
data class UserResponse(
    val userId: Long,
    val email: String,
    val username: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map UserRequest to User
fun UserRequest.toEntity(): User {
    return User(
        email = this.email,
        passwordHash = this.passwordHash,
        username = this.username
    )
}

// Extension function to map User to UserResponse
fun User.toResponse(): UserResponse {
    return UserResponse(
        userId = this.userId,
        email = this.email,
        username = this.username,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

