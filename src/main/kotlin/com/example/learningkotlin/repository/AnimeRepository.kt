package com.example.learningkotlin.repository

import com.example.learningkotlin.domain.entity.Anime
import org.springframework.data.jpa.repository.JpaRepository

interface AnimeRepository : JpaRepository<Anime, Long>