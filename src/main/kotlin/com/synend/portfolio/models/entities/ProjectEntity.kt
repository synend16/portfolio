package com.synend.portfolio.models.entities

import java.time.ZonedDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "PROJECT")
class ProjectEntity(

        @get:Id
        @get:GeneratedValue
        var id: Long? = null,

        @get:NotBlank
        var url: String? = null,

        @get:NotBlank
        var title: String? = null,

        @get:ElementCollection
        @get:NotNull
        var topics: Set<String>? = setOf(),

        var description: String? = null,

        @get:NotNull
        var lastUpdated: ZonedDateTime? = null

)
