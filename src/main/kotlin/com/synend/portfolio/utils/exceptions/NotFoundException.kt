package com.synend.portfolio.utils.exceptions

class NotFoundException(
        message: String,
        val httpCode : Int = 404
) : RuntimeException(message)