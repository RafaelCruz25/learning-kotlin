package com.example.learningkotlin.domain

import javax.validation.constraints.NotBlank

data class AnimeRequest(
    @field:NotBlank
    var name: String = ""
)
