package com.cba.sample.feature.style.ui.components.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cba.sample.R
import com.cba.sample.designsystems.components.containers.SectionContainer
import com.cba.sample.feature.style.ui.components.core.color.ColorPickerField
import com.cba.sample.feature.style.ui.components.core.counter.NumberCounter
import com.cba.sample.feature.style.ui.components.properties.button.BorderStrokeEditor
import com.cba.sample.feature.style.ui.components.properties.button.ElevationDropdown
import com.cba.sample.feature.style.ui.components.properties.padding.PaddingEditor
import com.cba.sample.feature.style.ui.components.properties.shape.ShapeDropdown
import com.paydock.designsystems.components.button.ButtonAppearance

@Composable
fun FilledButtonAppearanceStyleEditor(
    currentAppearance: ButtonAppearance.FilledButtonAppearance,
    onAppearanceChange: (ButtonAppearance.FilledButtonAppearance) -> Unit
) {
    // Derived states remain essential for UDF
    val currentHeight = currentAppearance.height
    val currentContentPadding = currentAppearance.contentPadding
    val currentContentSpacing = currentAppearance.contentSpacing
    val currentElevation = currentAppearance.elevation
    val currentShape = currentAppearance.shape
    val currentBorder = currentAppearance.border
    val currentColors = currentAppearance.colors
    val currentContentColor = currentColors.contentColor
    val currentContainerColor = currentColors.containerColor
    val currentDisabledContentColor = currentColors.disabledContentColor
    val currentDisabledContainerColor = currentColors.disabledContainerColor

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        SectionContainer(title = stringResource(R.string.label_button_colours)) {
            ColorPickerField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.label_content_colour),
                currentColor = currentContentColor,
                onColorChange = { newColor ->
                    onAppearanceChange(
                        currentAppearance.copy(
                            colors = currentAppearance.colors.copy(
                                contentColor = newColor
                            )
                        )
                    )
                }
            )

            ColorPickerField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.label_container_colour),
                currentColor = currentContainerColor,
                onColorChange = { newColor ->
                    onAppearanceChange(
                        currentAppearance.copy(
                            colors = currentAppearance.colors.copy(
                                containerColor = newColor
                            )
                        )
                    )
                }
            )

            ColorPickerField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.label_disabled_content_colour),
                currentColor = currentDisabledContentColor,
                onColorChange = { newColor ->
                    onAppearanceChange(
                        currentAppearance.copy(
                            colors = currentAppearance.colors.copy(
                                disabledContentColor = newColor
                            )
                        )
                    )
                }
            )

            ColorPickerField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.label_disabled_container_colour),
                currentColor = currentDisabledContainerColor,
                onColorChange = { newColor ->
                    onAppearanceChange(
                        currentAppearance.copy(
                            colors = currentAppearance.colors.copy(
                                disabledContainerColor = newColor
                            )
                        )
                    )
                }
            )
        }

        HorizontalDivider()

        NumberCounter(
            title = stringResource(R.string.label_height),
            value = currentHeight.value.toInt(),
            onValueChange = { newHeight ->
                onAppearanceChange(
                    currentAppearance.copy(height = newHeight.dp)
                )
            }
        )

        HorizontalDivider()

        NumberCounter(
            title = stringResource(R.string.label_content_spacing),
            value = currentContentSpacing.value.toInt(),
            onValueChange = { newContentSpacing ->
                onAppearanceChange(
                    currentAppearance.copy(contentSpacing = newContentSpacing.dp)
                )
            }
        )

        HorizontalDivider()

        PaddingEditor(
            title = stringResource(R.string.label_content_padding),
            currentPaddingValues = currentContentPadding,
            onPaddingChange = { newPadding ->
                onAppearanceChange(currentAppearance.copy(contentPadding = newPadding))
            }
        )

        HorizontalDivider()

        ElevationDropdown(
            currentElevation = currentElevation,
            onElevationChange = { newElevation ->
                onAppearanceChange(currentAppearance.copy(elevation = newElevation))
            }
        )

        HorizontalDivider()

        ShapeDropdown(
            containerLabel = stringResource(R.string.label_shape),
            currentShape = currentShape,
            onShapeChange = { newShape ->
                onAppearanceChange(currentAppearance.copy(shape = newShape))
            }
        )

        HorizontalDivider()

        if (currentBorder != null) {
            BorderStrokeEditor(
                currentBorderStroke = currentBorder,
                onBorderStrokeChange = { newBorderStroke ->
                    onAppearanceChange(currentAppearance.copy(border = newBorderStroke))
                }
            )
        }
    }
}

