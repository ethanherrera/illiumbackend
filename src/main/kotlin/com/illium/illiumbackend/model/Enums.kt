package com.illium.illiumbackend.model

/**
 * For Recurring Rules frequency: Daily, Weekly, Monthly, Yearly.
 */
enum class Frequency {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}

/**
 * Status for EventTechniques:
 * 0 = WAITING, 1 = COMPLETED, 2 = SKIPPED
 */
enum class EventTechniqueStatus {
    WAITING,
    COMPLETED,
    SKIPPED
}

/**
 * Status for LessonQueueTechniques:
 * 0 = WAITING, 1 = IN_PROGRESS, 2 = FINISHED
 */
enum class LessonQueueTechniqueStatus {
    WAITING,
    IN_PROGRESS,
    FINISHED
}
