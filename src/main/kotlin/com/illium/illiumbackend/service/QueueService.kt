package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.Queue
import com.illium.illiumbackend.repository.QueueRepository
import org.springframework.stereotype.Service

@Service
class QueueService(
    private val queueRepository: QueueRepository
) {

    fun getAllQueues(): List<Queue> = queueRepository.findAll()

    fun getQueueById(id: Long): Queue =
        queueRepository.findById(id).orElseThrow { Exception("Queue not found") }

    fun createQueue(queue: Queue): Queue = queueRepository.save(queue)

    fun deleteQueue(id: Long) = queueRepository.deleteById(id)
}