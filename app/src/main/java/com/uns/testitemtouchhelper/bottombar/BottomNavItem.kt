package com.uns.testitemtouchhelper.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    val id: Int = -1,
    @DrawableRes
    val icon: Int = 0,
    @StringRes
    val text: Int = 0
)
