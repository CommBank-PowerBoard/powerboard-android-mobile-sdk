package com.cba.sample.feature.settings.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cba.sample.BuildConfig
import com.cba.sample.R
import com.cba.sample.designsystems.components.containers.SectionContainer
import com.cba.sample.designsystems.components.fields.CopyTextField

@Preview
@Composable
fun SecretKeySection() {
    SectionContainer(title = stringResource(R.string.label_secret_key)) {
        // TODO - This will need to be updated with SDK Key values
        CopyTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = false,
            value = BuildConfig.SECRET_KEY
        )
    }
}