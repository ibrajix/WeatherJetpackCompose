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
package com.example.androiddevchallenge.components

import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.androiddevchallenge.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.ArrayList

/**
 * Function for displaying UI which is called in the MainActivity
 */
@Composable
fun DisplayView(onToggle: () -> Unit) {

    Surface(color = MaterialTheme.colors.background) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 24.dp, 36.dp, 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // switch theme section
                SwitchThemeUi(onToggle = { onToggle() })
            }

            Text(
                text = stringResource(id = R.string.chicago),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 35.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                textAlign = TextAlign.Start

            )

            Row {

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_sunshine),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 40.dp, top = 10.dp)
                        .requiredSize(28.dp)
                )

                Text(
                    text = stringResource(id = R.string.temperature),
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 35.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, top = 0.dp),
                    textAlign = TextAlign.Start

                )
            }

            CustomChart()

            // use lottie animation by calling LottieAnimationView() or just use normal vector image like below
            ImageSection()
        }
    }
}

/**
 * Function for switching theme to light or dark mode
 */

@Composable
fun SwitchThemeUi(onToggle: () -> Unit) {
    val icon = if (isSystemInDarkTheme())
        painterResource(id = R.drawable.ic_moon_light)
    else
        painterResource(id = R.drawable.ic_moon_dark)

    Icon(
        painter = icon, contentDescription = null,
        modifier = Modifier
            .size(36.dp, 36.dp)
            .clickable(onClick = onToggle)
    )
}

/**
 * Custom line chart implemented with the help of MP Android Library
 */

@Composable
fun CustomChart() {

    val entries = ArrayList<Entry>()

    val context = LocalContext.current

    AndroidView(

        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(30.dp),

        factory = { ctx ->
            LineChart(ctx).apply {
            }
        },

        update = {

            // add entry to chart
            entries.add(Entry(1f, 10f))
            entries.add(Entry(2f, 2f))
            entries.add(Entry(3f, 7f))
            entries.add(Entry(4f, 20f))
            entries.add(Entry(5f, 16f))

            val vl = LineDataSet(entries, context.getString(R.string.temperature))

            vl.setDrawValues(false)
            vl.setDrawCircles(false)
            vl.setDrawFilled(true)

            vl.lineWidth = 3f

            vl.color = ContextCompat.getColor(context, R.color.secondary_700)

            vl.fillColor = ContextCompat.getColor(context, R.color.secondary)

            it.xAxis.labelRotationAngle = 0f

            it.data = LineData(vl)

            // set touch and zoom enabled here

            it.setTouchEnabled(true)
            it.setPinchZoom(true)

            it.animateX(1800, Easing.EaseInExpo)

            it.axisLeft.textColor = ContextCompat.getColor(context, R.color.on_surface)
            it.xAxis.textColor = ContextCompat.getColor(context, R.color.on_surface)
            it.legend.textColor = ContextCompat.getColor(context, R.color.on_surface)
            it.description.textColor = ContextCompat.getColor(context, R.color.on_surface)

            it.description.text = context.getString(R.string.days)
            it.setNoDataText(context.getString(R.string.no_data))
        }
    )
}

/**
 * Animation view for displaying animated file in the res/raw directory
 */

// added lottie animation view here which can be called in a composable
@Composable
fun LottieAnimationView() {

    AndroidView(

        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(30.dp),

        factory = { ctx ->
            LottieAnimationView(ctx).apply {
            }
        },

        update = {

            it.setAnimation(R.raw.sun_rain)
            it.playAnimation()

            it.repeatMode = LottieDrawable.RESTART
            it.foregroundGravity = Gravity.CENTER
        }
    )
}

/**
 * Function for displaying an Image
 */

@Composable
fun ImageSection() {

    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_cold_hot),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 30.dp),
        alignment = Alignment.BottomEnd
    )
}
