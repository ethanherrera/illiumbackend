package com.illium.illiumbackend.controller

import com.illium.illiumbackend.model.Technique
import com.illium.illiumbackend.service.TechniqueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/techniques")
class TechniqueController(private val techniqueService: TechniqueService) {

    @GetMapping
    fun getAllTechniques(): List<Technique> = techniqueService.getAllTechniques()

    @GetMapping("/{id}")
    fun getTechniqueById(@PathVariable id: Long): Technique = techniqueService.getTechniqueById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTechnique(@RequestBody technique: Technique): Technique = techniqueService.createTechnique(technique)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTechnique(@PathVariable id: Long) = techniqueService.deleteTechnique(id)
}
