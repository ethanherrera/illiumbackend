package com.illium.illiumbackend.controller

// create a springboot controller for the event service
import com.illium.illiumbackend.model.Event
import com.illium.illiumbackend.model.EventRequest
import com.illium.illiumbackend.model.EventResponse
import com.illium.illiumbackend.service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class EventController(
    private val eventService: EventService
) {

    @GetMapping
    fun getAllEvents() : ResponseEntity<List<EventResponse>> {
        return try {
            ResponseEntity(eventService.getAllEvents(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

     @PostMapping
     fun createEvent(@RequestBody eventRequest: EventRequest) : ResponseEntity<EventResponse> {
         return try {
             val event = eventService.createEvent(eventRequest)
             ResponseEntity(event, HttpStatus.CREATED)
         } catch (e: Exception) {
             ResponseEntity(HttpStatus.BAD_REQUEST)
         }

     }

    @PutMapping("/{id}")
    fun editEvent(@RequestBody eventRequest: EventRequest, @PathVariable id: Long) : ResponseEntity<EventResponse> {
        return try {
            val event = eventService.editEvent(eventRequest, id)
            ResponseEntity(event, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: Long) : ResponseEntity<Unit> {
        return try {
            eventService.deleteEvent(id)
            ResponseEntity(Unit, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}