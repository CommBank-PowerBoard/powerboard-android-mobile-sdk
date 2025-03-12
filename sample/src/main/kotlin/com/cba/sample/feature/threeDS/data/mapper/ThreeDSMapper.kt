package com.cba.sample.feature.threeDS.data.mapper

import com.cba.sample.feature.threeDS.data.api.dto.ThreeDSTokenResponse
import com.cba.sample.feature.threeDS.domain.model.ThreeDSToken

fun ThreeDSTokenResponse.mapToDomain(): ThreeDSToken = ThreeDSToken(
    token = this.resource.resourceData.threeDS.token,
    status = ThreeDSToken.ThreeDSStatus.byNameIgnoreCaseOrNull(this.resource.resourceData.status),
    id = this.resource.resourceData.threeDS.id
)