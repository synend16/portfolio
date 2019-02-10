package com.synend.portfolio.models.dtos.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("DTO representing a Authentication object")
class AuthenticationDto(

        @ApiModelProperty("Username")
        var username: String? = null,

        @ApiModelProperty("Password")
        var password: String? = null
)