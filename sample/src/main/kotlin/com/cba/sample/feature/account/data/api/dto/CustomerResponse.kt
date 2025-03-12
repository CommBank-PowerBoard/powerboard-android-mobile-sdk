package com.cba.sample.feature.account.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class CustomerResponse(
    val error: Any,
    val resource: Resource<CustomerDTO>,
    val status: Int,
)
