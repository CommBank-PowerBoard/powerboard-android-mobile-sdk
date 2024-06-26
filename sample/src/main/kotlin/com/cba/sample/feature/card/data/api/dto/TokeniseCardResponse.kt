package com.cba.sample.feature.card.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class TokeniseCardResponse(
    val resource: Resource<String>,
    val status: Int
)
