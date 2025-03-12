package com.cba.sample.feature.threeDS.domain.usecase

import com.cba.sample.core.extensions.suspendRunCatching
import com.cba.sample.feature.threeDS.data.api.dto.Capture3DSChargeRequest
import com.cba.sample.feature.threeDS.domain.repository.ThreeDSRepository
import javax.inject.Inject

class CaptureThreeDSChargeTokenUseCase @Inject constructor(private val repository: ThreeDSRepository) {

    suspend operator fun invoke(request: Capture3DSChargeRequest) =
        suspendRunCatching {
            repository.capture3DSCharge(request)
        }
}