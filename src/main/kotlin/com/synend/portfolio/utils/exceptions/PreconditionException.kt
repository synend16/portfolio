package com.synend.portfolio.utils.exceptions

class PreconditionException(
        message: String,
        val httpCode : Int = 412
) : RuntimeException(message)