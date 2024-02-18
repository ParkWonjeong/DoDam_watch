// NotificationActivity.kt

package com.example.dodam_watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .background(Color(android.graphics.Color.parseColor("#FFB2A5"))),
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

// NotificationActivity.kt

@Composable
fun NotificationItem(notification: Notification) {
    var isExpanded by remember { mutableStateOf(false) }

    // 각 알림 아이템
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Button(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                if (isExpanded) Color(android.graphics.Color.parseColor("#ecd0d0")) else Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#D9D9D9")))
        ) {
            // 버튼 내용
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = notification.title, color = Color.Black, fontSize = 11.sp)
            }
        }

        // 상세 내용 보여주기
        if (isExpanded) {
            // 여기에 상세 내용을 보여주는 UI를 추가할 수 있습니다.
            // 예를 들어, Text 또는 다른 Composable을 사용하여 상세 내용을 표시할 수 있습니다.
            Text(
                text = notification.content,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
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
    Notification("Hospital appointment", "We have to go now!"),
    Notification("New Information", "There are some new tips!"),
    Notification("Time for Exercise", "Running Time!"),
    Notification("알림 4", "알림 내용 4"),
    Notification("알림 5", "알림 내용 5"),
)