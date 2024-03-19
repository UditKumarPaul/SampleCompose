package com.example.compose.mvvm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    then(clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    })
}