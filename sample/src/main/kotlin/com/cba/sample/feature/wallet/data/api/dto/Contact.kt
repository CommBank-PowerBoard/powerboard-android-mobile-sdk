package com.cba.sample.feature.wallet.data.api.dto

import com.cba.sample.core.FIRST_NAME
import com.cba.sample.core.LAST_NAME
import com.cba.sample.core.PHONE_NUMBER
import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("first_name") val firstName: String = FIRST_NAME,
    @SerializedName("last_name") val lastName: String = LAST_NAME,
    val phone: String = PHONE_NUMBER
)