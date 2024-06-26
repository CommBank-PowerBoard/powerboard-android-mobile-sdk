package com.cba.sample.feature.card.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class VaultTokenResponse(
    val resource: Resource<VaultData>,
    val status: Int
)
