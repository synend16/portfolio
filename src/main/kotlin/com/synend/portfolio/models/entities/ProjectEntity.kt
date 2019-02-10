package com.synend.portfolio.models.entities

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "PROJECT")
class ProjectEntity(

        @get:Id
        @get:GeneratedValue
        var id: Long? = null,

        var url: String? = null,

        @get:NotBlank
        var title: String? = null,

        @get:ElementCollection
        @get:NotNull
        var topics: Set<String>? = setOf(),

        @get:NotBlank
        var description: String? = null

)
