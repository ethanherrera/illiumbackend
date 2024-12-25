package com.illium.illiumbackend.controller

import com.illium.illiumbackend.model.GymClass
import com.illium.illiumbackend.service.GymClassService
import jakarta.validation.constraints.Null
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.http.HttpResponse

@RestController
@RequestMapping("/api/classes")
class GymClassController(
    private val gymClassService: GymClassService
) {

    @GetMapping
    fun getAllClasses(): List<GymClass> = gymClassService.getAllClasses()

    @GetMapping("/{id}")
    fun getClassById(@PathVariable id: Long): GymClass = gymClassService.getClassById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createClass(@RequestBody gymClass: GymClass,
                    @RequestParam(required = false,
                                  defaultValue = "false") default: Boolean): ResponseEntity<Any> {
        try {
            val createdClass: GymClass
            if (default) {
                createdClass = gymClassService.createDefaultClass(gymClass)
                return ResponseEntity(createdClass, HttpStatus.OK)
            }
            createdClass = gymClassService.createClass(gymClass)
            return ResponseEntity(createdClass, HttpStatus.OK)
        } catch (e: Exception) {
          return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClass(@PathVariable id: Long) = gymClassService.deleteClass(id)
}
