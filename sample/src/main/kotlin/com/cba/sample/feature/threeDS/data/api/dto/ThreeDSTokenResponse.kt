package com.cba.sample.feature.threeDS.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class ThreeDSTokenResponse(
    val resource: Resource<ThreeDSResourceData>,
    val status: Int
)
