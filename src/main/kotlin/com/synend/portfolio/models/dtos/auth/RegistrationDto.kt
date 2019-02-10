package com.synend.portfolio.models.dtos.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("DTO representing a Registration object")
class RegistrationDto(

        @ApiModelProperty("Username")
        var username: String? = null,

        @ApiModelProperty("Password")
        var password: String? = null,

        @ApiModelProperty("First name")
        var firstName: String?,

        @ApiModelProperty("Last name")
        var lastName: String?
)