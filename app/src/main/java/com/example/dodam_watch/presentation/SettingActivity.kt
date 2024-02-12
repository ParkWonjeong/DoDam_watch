// SettingActivity.kt

package com.example.dodam_watch.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dodam_watch.presentation.theme.DoDam_watchTheme

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingScreen(this)
        }
    }
}

@Composable
fun SettingScreen(activity: ComponentActivity) {
    DoDam_watchTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor("#FFB2A5"))),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn{
                item {
                    // 상단 제목
                    Text(
                        text = "Settings",
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

                // 버튼 추가
                item {
                    Button(
                        onClick = {// "계정 설정" 버튼 클릭 시 SettingAccountActivity로 이동
                            val intent = Intent(activity, SettingAccountActivity::class.java)
                            activity.startActivity(intent)},
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.WHITE)),
                        border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#D9D9D9")))
                    ) {
                        Text("계정 설정", color = Color.Black)
                    }
                }

                item {
                    Button(
                        onClick = { /* 버튼 클릭 시 동작 */ },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.WHITE)),
                        border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#D9D9D9")))
                    ) {
                        Text("앱 설정", color = Color.Black)
                    }
                }

                item {
                    Button(
                        onClick = { /* 버튼 클릭 시 동작 */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp),
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.WHITE)),
                        border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#D9D9D9")))
                    ) {
                        Text("기타 설정", color = Color.Black)
                    }
                }
                }
            }
        }
    }




@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(LocalContext.current as ComponentActivity)
}
