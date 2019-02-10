package com.synend.portfolio.models.dtos

import com.synend.portfolio.utils.hal.PageDto

class ResponseDto<T> (
		code: Int? = null,
		page: PageDto<T>? = null,
		message: String? = null,
		status: ResponseStatus? = null
) : WrappedResponse<T>(code, page, message, status)