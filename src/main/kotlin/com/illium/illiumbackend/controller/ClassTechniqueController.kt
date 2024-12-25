package com.illium.illiumbackend.controller

import com.illium.illiumbackend.model.ClassTechnique
import com.illium.illiumbackend.service.ClassTechniqueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/class-techniques")
class ClassTechniqueController(
    private val classTechniqueService: ClassTechniqueService
) {

    @GetMapping
    fun getAllClassTechniques(): List<ClassTechnique> = classTechniqueService.getAllClassTechniques()

    @GetMapping("/{id}")
    fun getClassTechniqueById(@PathVariable id: Long): ClassTechnique =
        classTechniqueService.getClassTechniqueById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createClassTechnique(@RequestBody ct: ClassTechnique): ClassTechnique =
        classTechniqueService.createClassTechnique(ct)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClassTechnique(@PathVariable id: Long) =
        classTechniqueService.deleteClassTechnique(id)
}