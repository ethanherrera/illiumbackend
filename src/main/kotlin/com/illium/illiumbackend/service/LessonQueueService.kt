package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.*
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

    fun createLessonQueue(lessonQueueRequest: LessonQueueRequest) : LessonQueueResponse {
        val lessonQueue = lessonQueueRequest.toEntity()
        lessonQueueRepository.save(lessonQueue)
        return lessonQueue.toResponse()
    }

    fun editLessonQueue(lessonQueueRequest: LessonQueueRequest, id: Long) : LessonQueueResponse {
        val lessonQueue = lessonQueueRepository.findById(id).orElseThrow {
            Exception("Technique with id $id not found")
        }
        lessonQueue.name = lessonQueueRequest.name
        lessonQueue.level = lessonQueueRequest.level
        lessonQueueRepository.save(lessonQueue)
        return lessonQueue.toResponse()
    }

    fun deleteLessonQueue(id: Long) {
        lessonQueueRepository.deleteById(id)
    }

    fun getAllLessonQueues() : List<LessonQueueResponse> {
        return lessonQueueRepository.findAll().map { it.toResponse() }
    }

}