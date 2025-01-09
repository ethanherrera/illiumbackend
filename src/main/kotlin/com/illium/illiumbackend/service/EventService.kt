package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.*
import com.illium.illiumbackend.repository.EventRepository
import org.springframework.stereotype.Service
import java.lang.Exception
import kotlin.math.min

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val lessonQueueService: LessonQueueService,
) {
    fun createEvent(eventRequest: EventRequest) : Event {
        // Create event and its Recurring rule if it exists
        val event = eventRequest.toEntity()
        if (event.isRecurring && eventRequest.recurringRule != null) {
            val recurringRule = eventRequest.recurringRule.toEntity()
            event.recurringRule = recurringRule
        }

        // Fetches correct queue and creates EventQueue entity
        val lessonQueue = lessonQueueService.findLessonQueueByLevel(level = event.level)
        if (lessonQueue.isEmpty()) {
            throw Exception("LessonQueue with level ${event.level} not found")
        }
        val eventQueue = EventQueue(event = event, lessonQueue = lessonQueue[0])
        event.eventQueues.add(eventQueue)

        // Save the event
        eventRepository.save(event)

        return event
    }


    fun editEvent(eventRequest: EventRequest, id: Long) : Event {
        // Find the event to edit
        val event = eventRepository.findById(id).orElseThrow {
            Exception("Event with id $id not found")
        }

        // Update the event with the new values
        event.name = eventRequest.name
        event.description = eventRequest.description ?: "Add a description here"
        event.startTime = eventRequest.startTime
        event.endTime = eventRequest.endTime
        event.isRecurring = eventRequest.isRecurring
        event.level = eventRequest.level

        // Update the recurring rule if it exists
        if (event.isRecurring && eventRequest.recurringRule != null) {
            val recurringRule = eventRequest.recurringRule.toEntity()
            event.recurringRule = recurringRule
        }

        // Delete and add new event queue if level changed
        if (event.level != eventRequest.level) {
            val lessonQueue = lessonQueueService.findLessonQueueById(id = eventRequest.level.toLong())
            val eventQueue = EventQueue(event = event, lessonQueue = lessonQueue)
            event.eventQueues.clear()
            event.eventQueues.add(eventQueue)
        }

        // Save the updated event
        eventRepository.save(event)

        return event
    }

    fun deleteEvent(id: Long) {
        val event = eventRepository.findById(id).orElseThrow {
            Exception("Event with id $id not found")
        }
        eventRepository.delete(event)
    }


}