package com.example.learningkotlin.controller

import com.example.learningkotlin.domain.AnimeRequest
import com.example.learningkotlin.domain.entity.Anime
import com.example.learningkotlin.service.AnimeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping("animes")
class AnimeController(
    private val animeService: AnimeService
) {
    companion object {
        private const val maxElements = 50L
    }

    @GetMapping
    fun listAll(
        @Valid @Min(1) @RequestParam(required = false, defaultValue = "1") page: Int,
        @Valid @Min(1) @Max(maxElements) @RequestParam(required = false, defaultValue = "20") size: Int = 20
    ): ResponseEntity<List<Anime>> {
        val list = animeService.listAll(page, size)

        return ResponseEntity.ok(list)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Anime> {
        val anime = animeService.findById(id)

        return ResponseEntity.ok(anime)
    }

    @PostMapping
    fun createAnime(@Valid @RequestBody request: AnimeRequest): ResponseEntity<Any?> {
        val id = animeService.createAnime(request)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .build(id)

        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any?> {
        animeService.deleteById(id)

        return ResponseEntity.noContent().build()
    }

    @PutMapping("{id}")
    fun updateById(@PathVariable id: Long, @Valid @RequestBody request: AnimeRequest): ResponseEntity<Any?> {
        animeService.updateById(id, request)

        return ResponseEntity.noContent().build()
    }
}