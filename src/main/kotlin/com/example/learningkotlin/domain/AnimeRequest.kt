package com.example.learningkotlin.domain

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AnimeRequest(
    @field:NotBlank
    val name: String,
    @field:NotNull
    val bornAt: LocalDate = LocalDate.now()
)
