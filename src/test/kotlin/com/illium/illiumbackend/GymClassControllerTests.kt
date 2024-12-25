package com.illium.illiumbackend

import com.illium.illiumbackend.controller.GymClassController
import com.illium.illiumbackend.model.GymClass
import com.illium.illiumbackend.model.Queue
import com.illium.illiumbackend.service.GymClassService
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import kotlin.test.Test
import kotlin.test.assertEquals

class GymClassControllerTests {

    private val gymClassService: GymClassService = mock(GymClassService::class.java)
    private val gymClassController = GymClassController(gymClassService)

    @Test
    fun `createClass should return created GymClass with default as true`() {
        // Arrange
        val gymClass = GymClass(name = "VKM Level 1")
        val savedClass = gymClass.copy(id = 1L)

        `when`(gymClassService.createDefaultClass(gymClass)).thenReturn(savedClass)

        // Act
        val response = gymClassController.createClass(gymClass)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(savedClass, response.body)
        verify(gymClassService).createDefaultClass(gymClass)
    }

    @Test
    fun `createClass should return created GymClass with default as false`() {
        // Arrange
        val gymClass = GymClass(name = "VKM Level 2")
        val savedClass = gymClass.copy(id = 1L)

        `when`(gymClassService.createClass(gymClass)).thenReturn(savedClass)

        // Act
        val response = gymClassController.createClass(gymClass)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(savedClass, response.body)
        verify(gymClassService).createClass(gymClass)
    }

    @Test
    fun `createClass should handle internal server errors`() {
        // Arrange
        val gymClass = GymClass(name = "VKM Level 3")
        val exception = RuntimeException("Unexpected error")

        `when`(gymClassService.createClass(gymClass)).thenThrow(exception)

        // Act
        val response = gymClassController.createClass(gymClass)

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode)
        assertEquals(exception, response.body)
        verify(gymClassService).createClass(gymClass)
    }

    @Test
    fun `createClass with default queue should create class with a queue of all techniques`() {
        // Arrange
        val mockQueue = Queue(id = 1, name = "Default Queue")
        val mockGymClass = GymClass(id = 1, name = "Default Class", queue = mockQueue)

        val gymClass = GymClass(name = "Default Class")

        `when`(gymClassService.createDefaultClass(gymClass)).thenReturn(mockGymClass)

        // Act
        val response = gymClassController.createClass(gymClass)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(mockGymClass.name, (response.body as GymClass).name)
        assertEquals(mockGymClass.queue?.name, (response.body as GymClass).queue?.name)

        verify(gymClassService, times(1)).createDefaultClass(gymClass)

        println("Test passed: Created class '${mockGymClass.name}' with queue '${mockGymClass.queue?.name}'.")
    }
}