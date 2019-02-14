package com.synend.portfolio.models.entities

import com.synend.portfolio.models.ExperienceType
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "EXPERIENCE")
class ExperienceEntity(

        @get:Id
        @get:GeneratedValue
        var id: Long? = null,

        @get:NotBlank
        var title: String? = null,

        @get:NotBlank
        var description: String? = null,

        @get:NotNull
        var startYear: LocalDate? = null,

        @get:NotNull
        var endYear: LocalDate? = null,

        @get:Enumerated(EnumType.STRING)
        var experienceType: ExperienceType? = null
)