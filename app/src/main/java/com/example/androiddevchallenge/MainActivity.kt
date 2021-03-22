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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.components.DisplayView
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.TransparentStatusBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // check current theme and change via toggle
            val currentTheme = isSystemInDarkTheme()

            if (!isSystemInDarkTheme()) {
                TransparentStatusBar(windows = window)
            }

            val changeTheme: () -> Unit = {
                if (currentTheme) {
                    setDayTheme()
                } else {
                    setDarkTheme()
                }
            }

            MyTheme {

                MyApp(changeTheme)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(changeTheme: () -> Unit) {
    DisplayView(onToggle = changeTheme)
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(changeTheme = {})
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(changeTheme = {})
    }
}

private fun setDayTheme() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}

private fun setDarkTheme() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
}
