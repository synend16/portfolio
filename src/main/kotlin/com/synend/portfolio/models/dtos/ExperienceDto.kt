package com.synend.portfolio.models.dtos

import com.synend.portfolio.models.ExperienceType
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ApiModel("Model object representing an Experience (Education or Work)")
class ExperienceDto(

        @ApiModelProperty("Id of an experience")
        var id: String? = null,

        @ApiModelProperty("Title of an experience")
        var title: String? = null,

        @ApiModelProperty("Description of an experience")
        var description: String? = null,

        @ApiModelProperty("Start year of an experience")
        var startYear: String? = null,

        @ApiModelProperty("End year of an experience")
        var endYear: String? = LocalDate
                .now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .toString(),

        @ApiModelProperty("Enum representing the type of experience")
        var experienceType: ExperienceType? = ExperienceType.WORK
)