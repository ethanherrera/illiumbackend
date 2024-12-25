package com.illium.illiumbackend.controller

import com.illium.illiumbackend.model.QueueTechnique
import com.illium.illiumbackend.service.QueueTechniqueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/queue-techniques")
class QueueTechniqueController(
    private val queueTechniqueService: QueueTechniqueService
) {

    @GetMapping
    fun getAllQueueTechniques(): List<QueueTechnique> = queueTechniqueService.getAllQueueTechniques()

    @GetMapping("/{id}")
    fun getQueueTechniqueById(@PathVariable id: Long): QueueTechnique =
        queueTechniqueService.getQueueTechniqueById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createQueueTechnique(@RequestBody queueTechnique: QueueTechnique): QueueTechnique =
        queueTechniqueService.createQueueTechnique(queueTechnique)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteQueueTechnique(@PathVariable id: Long) =
        queueTechniqueService.deleteQueueTechnique(id)
}
