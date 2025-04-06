package com.creospace.recipe_kmp.components

import android.print.PrintAttributes.Margins
import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Margin(modifier: Modifier = Modifier, size: Dp) {
    Spacer(modifier = Modifier.height(size))
}