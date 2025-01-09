package com.illium.illiumbackend.repository

import com.illium.illiumbackend.model.LessonQueue
import org.springframework.data.jpa.repository.JpaRepository

interface LessonQueueRepository : JpaRepository<LessonQueue, Long> {
    fun findByLevel(level: Int) : List<LessonQueue>
}