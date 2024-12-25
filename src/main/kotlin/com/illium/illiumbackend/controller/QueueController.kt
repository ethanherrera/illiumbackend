package com.illium.illiumbackend.controller

import com.illium.illiumbackend.model.Queue
import com.illium.illiumbackend.service.QueueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/queues")
class QueueController(
    private val queueService: QueueService
) {

    @GetMapping
    fun getAllQueues(): List<Queue> = queueService.getAllQueues()

    @GetMapping("/{id}")
    fun getQueueById(@PathVariable id: Long): Queue = queueService.getQueueById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createQueue(@RequestBody queue: Queue): Queue = queueService.createQueue(queue)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteQueue(@PathVariable id: Long) = queueService.deleteQueue(id)
}