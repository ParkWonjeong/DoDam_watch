
package com.example.dodam_watch.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.TimeText
import com.example.dodam_watch.R
import com.example.dodam_watch.presentation.theme.DoDam_watchTheme

class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Alarm_screen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Alarm_screen() {
    DoDam_watchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor("#FFB2A5"))),
            contentAlignment = Alignment.Center
        ) {
            TimeText()
            Text(
                text = stringResource(R.string.AlarmTitle),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 90.dp)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.AlarmTime),
                color = Color(android.graphics.Color.parseColor("#000000")),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.AlarmInfo),
                color = Color(android.graphics.Color.parseColor("#000000")),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = { /* 버튼 클릭 시 동작 정의 */ },
                modifier = Modifier
                    .size(45.dp)
                    .offset(y = 60.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#FF9793")),
                        shape = CircleShape
                    )
            ) {
                // X 표시 아이콘
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color(android.graphics.Color.parseColor("#FC002B")),
                    modifier = Modifier.size(23.dp)
                )
            }
            }
        }
    }

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun AlarmPreview() {
    Alarm_screen()
}
