package com.example.learningkotlin.controller

import com.example.learningkotlin.domain.AnimeRequest
import com.example.learningkotlin.domain.entity.Anime
import com.example.learningkotlin.service.AnimeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("animes")
class AnimeController(
    private val animeService: AnimeService
) {

    @GetMapping
    fun listAll( @RequestParam(required = false, defaultValue = "1") page: Int,
                 @RequestParam(required = false, defaultValue = "20") size: Int = 20
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