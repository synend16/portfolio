package com.synend.portfolio.models.dtos

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime

@ApiModel("DTO representing a Project")
class ProjectDto(

        @ApiModelProperty("Project id")
        var id: String? = null,

        @ApiModelProperty("Project url")
        var url: String? = null,

        @ApiModelProperty("Project title")
        var title: String? = null,

        @ApiModelProperty("Project topics")
        var topics: MutableSet<String>? = null,

        @ApiModelProperty("Project description")
        var description: String? = null,

        @ApiModelProperty("Project lastUpdated")
        var lastUpdated: String? = null
)
