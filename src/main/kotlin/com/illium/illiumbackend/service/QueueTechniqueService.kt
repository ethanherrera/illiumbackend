package com.illium.illiumbackend.service

import com.illium.illiumbackend.model.Queue
import com.illium.illiumbackend.model.QueueTechnique
import com.illium.illiumbackend.repository.QueueTechniqueRepository
import org.springframework.stereotype.Service

@Service
class QueueTechniqueService(
    private val queueTechniqueRepository: QueueTechniqueRepository
) {

    fun getAllQueueTechniques(): List<QueueTechnique> = queueTechniqueRepository.findAll()

    fun saveAllQueueTechniques(qts: List<QueueTechnique>): List<QueueTechnique> = queueTechniqueRepository.saveAll(qts)

    fun getQueueTechniqueById(id: Long): QueueTechnique =
        queueTechniqueRepository.findById(id).orElseThrow { Exception("QueueTechnique not found") }

    fun createQueueTechnique(qt: QueueTechnique): QueueTechnique = queueTechniqueRepository.save(qt)

    fun deleteQueueTechnique(id: Long) = queueTechniqueRepository.deleteById(id)

    fun findTechniquesByQueueId(id: Long): List<QueueTechnique> = queueTechniqueRepository.findByQueueId(id)
}