package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.LessonQueue
import com.illium.illiumbackend.repository.LessonQueueRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class LessonQueueService(
    private val lessonQueueRepository: LessonQueueRepository
) {
    fun findLessonQueueById(id: Long) : LessonQueue = lessonQueueRepository.findById(id).orElseThrow {
        Exception("LessonQueue with id $id not found")
    }

    fun findLessonQueueByLevel(level: Int) : List<LessonQueue> = lessonQueueRepository.findByLevel(level)

}