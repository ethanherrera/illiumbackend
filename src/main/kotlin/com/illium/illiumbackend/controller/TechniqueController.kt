package com.illium.illiumbackend.controller

// create a springboot controller for the event service
import com.illium.illiumbackend.model.EventRequest
import com.illium.illiumbackend.model.EventResponse
import com.illium.illiumbackend.model.TechniqueRequest
import com.illium.illiumbackend.model.TechniqueResponse
import com.illium.illiumbackend.service.EventService
import com.illium.illiumbackend.service.TechniqueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/techniques")
class TechniqueController(
    private val techniqueService: TechniqueService
) {

    @GetMapping
    fun getAllTechniques() : ResponseEntity<List<TechniqueResponse>> {
        return try {
            val techniques = techniqueService.getAllTechniques()
            ResponseEntity(techniques, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping
    fun createTechnique(@RequestBody techniqueRequest: TechniqueRequest) : ResponseEntity<TechniqueResponse> {
        return try {
            val event = techniqueService.createTechnique(techniqueRequest)
            ResponseEntity(event, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }

    }

    @PutMapping("/{id}")
    fun editTechnique(@RequestBody techniqueRequest: TechniqueRequest, @PathVariable id: Long) : ResponseEntity<TechniqueResponse> {
        return try {
            val event = techniqueService.editTechnique(techniqueRequest, id)
            ResponseEntity(event, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTechnique(@PathVariable id: Long) : ResponseEntity<Unit> {
        return try {
            techniqueService.deleteTechnique(id)
            ResponseEntity(Unit, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}