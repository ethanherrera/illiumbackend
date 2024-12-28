package com.illium.illiumbackend.controller

import com.illium.illiumbackend.model.GymClass
import com.illium.illiumbackend.service.GymClassService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/classes")
class GymClassController(
    private val gymClassService: GymClassService
) {

    @Operation(summary = "Retrieve all gym classes", description = "Fetches a list of all gym classes available.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved the list of gym classes"),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @GetMapping
    fun getAllClasses(): List<GymClass> = gymClassService.getAllClasses()

    @Operation(summary = "Retrieve a gym class by ID", description = "Fetches the details of a specific gym class using its ID.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved the gym class details"),
            ApiResponse(responseCode = "404", description = "Gym class not found", content = [Content()])
        ]
    )
    @GetMapping("/{id}")
    fun getClassById(
        @Parameter(description = "ID of the gym class to retrieve", required = true)
        @PathVariable id: Long
    ): GymClass = gymClassService.getClassById(id)

    @Operation(summary = "Create a new gym class", description = "Creates a new gym class with the specified details.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Gym class successfully created",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = GymClass::class))]
            ),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createClass(
        @Parameter(description = "Gym class details", required = true)
        @RequestBody gymClass: GymClass
    ): ResponseEntity<Any> {
        return try {
            val createdClass = gymClassService.createClass(gymClass)
            ResponseEntity(createdClass, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Operation(summary = "Dequeue techniques from a class", description = "Dequeues a specified number of techniques from a class.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "202", description = "Successfully dequeued techniques"),
            ApiResponse(responseCode = "404", description = "Gym class not found", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @PostMapping("/deque")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun dequeTechnique(
        @Parameter(description = "ID of the gym class", required = true) @RequestParam gymClassId: Long,
        @Parameter(description = "Number of techniques to dequeue", required = false) @RequestParam(defaultValue = "5") amount: Int
    ): ResponseEntity<Any> {
        return try {
            val techniques = gymClassService.dequeTechniques(gymClassId, amount)
            ResponseEntity(techniques, HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Operation(summary = "Complete a class technique", description = "Marks a technique as completed for a specific class.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "202", description = "Successfully completed the technique"),
            ApiResponse(responseCode = "404", description = "Technique not found", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @PostMapping("/complete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun completeClassTechnique(
        @Parameter(description = "ID of the technique to mark as completed", required = true) @RequestParam id: Long
    ): ResponseEntity<Any> {
        return try {
            val completedTechnique = gymClassService.completeTechnique(id)
            ResponseEntity(completedTechnique, HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Operation(summary = "Skip a class technique", description = "Marks a technique as skipped for a specific class.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "202", description = "Successfully skipped the technique"),
            ApiResponse(responseCode = "404", description = "Technique not found", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @PostMapping("/skip")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun skipClassTechnique(
        @Parameter(description = "ID of the technique to mark as skipped", required = true) @RequestParam id: Long
    ): ResponseEntity<Any> {
        return try {
            val skippedTechnique = gymClassService.skipTechnique(id)
            ResponseEntity(skippedTechnique, HttpStatus.ACCEPTED)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Operation(summary = "Delete a gym class", description = "Deletes a gym class by its ID.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Gym class successfully deleted"),
            ApiResponse(responseCode = "404", description = "Gym class not found", content = [Content()])
        ]
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClass(
        @Parameter(description = "ID of the gym class to delete", required = true) @PathVariable id: Long
    ) = gymClassService.deleteClass(id)
}
