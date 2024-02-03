// NotificationActivity.kt

package com.example.dodam_watch.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.TimeText
import com.example.dodam_practice_wearos.presentation.Greeting
import com.example.dodam_watch.R
import com.example.dodam_watch.presentation.theme.DoDam_watchTheme

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen()
        }
    }
}

@Composable
fun NotificationScreen() {
    DoDam_watchTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.WHITE)),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn {
                item {
                    // 상단 제목
                    Text(
                        text = "Notifications",
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

                // 알림 목록
                items<Notification>(notificationList) { notification ->
                    NotificationItem(notification)
                }
            }
        }
    }
}

@Composable
fun NotificationItem(notification: Notification) {
    // 각 알림 아이템
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Button(
            onClick = { /* 버튼 클릭 시 동작 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#F2E8DA"))),
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, Color(android.graphics.Color.parseColor("#FFB2A5")))
        ) {
            // 버튼 내용
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = notification.content, color = Color.Black, fontSize = 11.sp)
            }
        }
    }
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}

data class Notification(val title: String, val content: String)

val notificationList = listOf(
    Notification("알림 1", "오늘은 병원 예약 날이에요!"),
    Notification("알림 2", "새로운 사진이 등록되었어요!"),
    Notification("알림 3", "오늘 알람이 설정되었어요!"),
    Notification("알림 4", "알림 내용 4"),
    Notification("알림 5", "알림 내용 5"),
)