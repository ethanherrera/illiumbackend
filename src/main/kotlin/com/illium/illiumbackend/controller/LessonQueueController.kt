package com.illium.illiumbackend.controller

// create a springboot controller for the event service
import com.illium.illiumbackend.model.*
import com.illium.illiumbackend.service.EventService
import com.illium.illiumbackend.service.LessonQueueService
import com.illium.illiumbackend.service.TechniqueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lesson-queues")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class LessonQueueController(
    private val lessonQueueService: LessonQueueService
) {

    @GetMapping
    fun getAllLessonQueues() : ResponseEntity<List<LessonQueueResponse>> {
        return try {
            val lessonQueues = lessonQueueService.getAllLessonQueues()
            ResponseEntity(lessonQueues, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping
    fun createLessonQueue(@RequestBody lessonQueueRequest: LessonQueueRequest) : ResponseEntity<LessonQueueResponse> {
        return try {
            val event = lessonQueueService.createLessonQueue(lessonQueueRequest)
            ResponseEntity(event, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }

    }

    @PutMapping("/{id}")
    fun editLessonQueue(@RequestBody lessonQueueRequest: LessonQueueRequest, @PathVariable id: Long) : ResponseEntity<LessonQueueResponse> {
        return try {
            val event = lessonQueueService.editLessonQueue(lessonQueueRequest, id)
            ResponseEntity(event, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteLessonQueue(@PathVariable id: Long) : ResponseEntity<Unit> {
        return try {
            lessonQueueService.deleteLessonQueue(id)
            ResponseEntity(Unit, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}