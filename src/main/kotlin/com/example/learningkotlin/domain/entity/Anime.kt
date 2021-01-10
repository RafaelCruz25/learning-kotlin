package com.example.learningkotlin.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Anime(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val bornAt: LocalDate,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null
)