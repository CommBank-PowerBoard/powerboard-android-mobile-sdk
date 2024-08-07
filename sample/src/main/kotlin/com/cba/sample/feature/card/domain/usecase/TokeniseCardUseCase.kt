package com.cba.sample.feature.card.domain.usecase

import com.cba.sample.core.extensions.suspendRunCatching
import com.cba.sample.feature.card.data.api.dto.TokeniseCardRequest
import com.cba.sample.feature.card.domain.repository.CardRepository
import javax.inject.Inject

class TokeniseCardUseCase @Inject constructor(private val repository: CardRepository) {

    suspend operator fun invoke(accessToken: String, request: TokeniseCardRequest) =
        suspendRunCatching {
            repository.tokeniseCardDetails(accessToken, request)
        }
}