package com.cba.sample.feature.wallet.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class WalletResponse(
    val error: Any,
    val resource: Resource<WalletData>,
    val status: Int
)