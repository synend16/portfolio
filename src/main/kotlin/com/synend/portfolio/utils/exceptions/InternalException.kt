package com.synend.portfolio.utils.exceptions

class InternalException(
        message: String,
        val httpCode : Int = 500
) : RuntimeException(message)