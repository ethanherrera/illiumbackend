package com.illium.illiumbackend.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "recurring_rules")
data class RecurringRule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", nullable = false, unique = true)
    val ruleId: Long = 0L,

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency", nullable = false)
    var frequency: Frequency,

    @Column(name = "freq_interval", nullable = false)
    var interval: Int,

    @Column(name = "days_of_week", nullable = false, length = 7)
    var daysOfWeek: String, // e.g., "1100101" which means Mon, Tue, Fri, Sun

    @Column(name = "end_date", nullable = true)
    var endDate: LocalDateTime? = null,

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

// Request DTO for RecurringRule
data class RecurringRuleRequest(
    val frequency: Frequency,
    val interval: Int,
    val daysOfWeek: String,
    val endDate: LocalDateTime?
)

// Response DTO for RecurringRule
data class RecurringRuleResponse(
    val ruleId: Long,
    val frequency: Frequency,
    val interval: Int,
    val daysOfWeek: String,
    val endDate: LocalDateTime?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

// Extension function to map RecurringRuleRequest to RecurringRule
fun RecurringRuleRequest.toEntity(): RecurringRule {
    return RecurringRule(
        frequency = this.frequency,
        interval = this.interval,
        daysOfWeek = this.daysOfWeek,
        endDate = this.endDate,
    )
}

// Extension function to map RecurringRule to RecurringRuleResponse
fun RecurringRule.toResponse(): RecurringRuleResponse {
    return RecurringRuleResponse(
        ruleId = this.ruleId,
        frequency = this.frequency,
        interval = this.interval,
        daysOfWeek = this.daysOfWeek,
        endDate = this.endDate,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

