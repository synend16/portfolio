package com.synend.portfolio.utils.exceptions

class ConflictException(
        message: String,
        val httpCode : Int = 409
) : RuntimeException(message)