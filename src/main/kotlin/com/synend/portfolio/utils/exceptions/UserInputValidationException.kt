package com.synend.portfolio.utils.exceptions

class UserInputValidationException(
        message: String,
        val httpCode : Int = 400
) : RuntimeException(message)