package com.example.learningkotlin.service

import com.example.learningkotlin.domain.AnimeRequest
import com.example.learningkotlin.domain.entity.Anime
import com.example.learningkotlin.exception.ExistingContentException
import com.example.learningkotlin.exception.NotFoundException
import com.example.learningkotlin.repository.AnimeRepository
import org.springframework.stereotype.Service

@Service
class AnimeService(private val animeRepository: AnimeRepository) {

    fun listAll(): List<Anime> {
        return animeRepository.findAll()
    }

    fun findById(id: Long): Anime {
        return animeRepository.findById(id).orElseThrow {
            NotFoundException("ID $id not found")
        }
    }

    fun createAnime(request: AnimeRequest): Long {
        if(animeRepository.existsByName(request.name)){
            throw ExistingContentException("Name ${request.name} already exists")
        }

        val anime = Anime(
            name = request.name,
            bornAt = request.bornAt
        )

        return animeRepository.save(anime).id
    }

    fun deleteById(id: Long) {
        if (animeRepository.existsById(id).not()) {
            throw NotFoundException("ID $id not found")
        }
        animeRepository.deleteById(id)
    }

    fun updateById(id: Long, request: AnimeRequest) {
        if (animeRepository.existsById(id).not()) {
            throw NotFoundException("ID $id not found")
        }

        animeRepository.findById(id).ifPresent {
            val animeUpdate = it.copy(
                name = request.name,
                bornAt = request.bornAt
            )
            animeRepository.save(animeUpdate)
        }
    }
}
