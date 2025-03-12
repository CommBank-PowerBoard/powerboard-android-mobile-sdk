package com.cba.sample.feature.card.data.api.dto

import com.cba.sample.core.data.api.dto.Resource
import com.google.gson.annotations.SerializedName

data class VaultTokenResponse(
    val resource: Resource<VaultDTO>,
    val status: Int,
) {
    data class VaultDTO(
        @SerializedName("vault_token") val token: String,
        // We do not need any other response properties (ie. charge)
    )
}
