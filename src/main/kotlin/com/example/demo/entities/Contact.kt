package com.example.demo.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "contacts")
class Contact(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @field:NotNull
        @field:Size(min = 5, max = 50)
        var name: String,

        @field:NotNull
        @field:Email
        var email: String
)