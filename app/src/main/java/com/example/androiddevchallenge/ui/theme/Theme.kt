/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(

    primary = primary_dark,
    primaryVariant = primary_variant_dark,
    secondary = secondary_dark,
    background = background_dark,
    surface = surface_dark,
    onPrimary = on_primary_dark,
    onSecondary = on_secondary_dark,
    onBackground = on_background_dark,
    onSurface = on_surface_dark,

)

private val LightColorPalette = lightColors(

    primary = primary_light,
    primaryVariant = primary_variant_light,
    secondary = secondary_light,
    background = background_light,
    surface = surface_light,
    onPrimary = on_primary_light,
    onSecondary = on_secondary_light,
    onBackground = on_background_light,
    onSurface = on_surface_light,

)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
