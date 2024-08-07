package com.cba.sample.feature.checkout.data.api.dto

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.cba.sample.core.extensions.toHexCode
import com.cba.sample.designsystems.theme.Border
import com.cba.sample.designsystems.theme.Primary

data class Base(
    @SerializedName("font_family") val fontFamily: String = "Roboto",
    @SerializedName("font_size") val fontSize: String = "14px",
    @SerializedName("background_color") val backgroundColor: String = Color.White.toHexCode(),
    @SerializedName("text_color") val textColor: String = Color.Black.toHexCode(),
    @SerializedName("border_color") val borderColor: String = Border.toHexCode(),
    @SerializedName("button_color") val buttonColor: String = Primary.toHexCode()
)