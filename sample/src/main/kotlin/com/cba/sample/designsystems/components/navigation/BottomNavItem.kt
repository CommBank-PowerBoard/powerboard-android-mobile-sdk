package com.cba.sample.designsystems.components.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cba.sample.R

sealed class BottomNavItem(
    @StringRes val label: Int,
    @DrawableRes val iconOutlined: Int,
    @DrawableRes val iconFilled: Int,
    val route: String,
) {
    data object Checkout : BottomNavItem(
        R.string.nav_checkout,
        R.drawable.ic_checkout,
        R.drawable.ic_checkout_selected,
        "checkout"
    )

    data object Widgets :
        BottomNavItem(
            R.string.nav_widgets,
            R.drawable.ic_widgets,
            R.drawable.ic_widgets_selected,
            "widgets"
        )

    data object Style :
        BottomNavItem(
            R.string.nav_style,
            R.drawable.ic_style,
            R.drawable.ic_style_selected,
            "style"
        )
}