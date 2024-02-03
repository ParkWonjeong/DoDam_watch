// HomeActivity.kt

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
import androidx.wear.compose.material.TimeText
import com.example.dodam_practice_wearos.presentation.Greeting
import com.example.dodam_watch.R
import com.example.dodam_watch.presentation.theme.DoDam_watchTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen("babyHome")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(babyName: String) {
    DoDam_watchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor("#D9D9D9"))),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    ){
                    TimeText()
                    Image(
                        painter = painterResource(id = R.drawable.baby_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.Center)
                            .offset(y = (-20).dp)
                            .background(
                                color = Color(android.graphics.Color.parseColor("#FFB2A5")),
                                shape = CircleShape,
                            )
                            .padding(2.dp)
                            .clip(CircleShape)
                    )
                    Greeting(babyName = babyName)
                }}

                // 버튼 추가
                item {
                    Button(
                        onClick = { /* 버튼 클릭 시 동작 */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#F2E8DA"))),
                        border = BorderStroke(1.dp, Color(android.graphics.Color.parseColor("#FFB2A5")))
                    ) {
                        Text("알림", color = Color.Black)
                    }
                }

                item {
                    Button(
                        onClick = { /* 버튼 클릭 시 동작 */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#F2E8DA"))),
                        border = BorderStroke(1.dp, Color(android.graphics.Color.parseColor("#FFB2A5")))
                    ) {
                        Text("건강 기록", color = Color.Black)
                    }
                }

                item {
                    Button(
                        onClick = { /* 버튼 클릭 시 동작 */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp),
                        colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#F2E8DA"))),
                        border = BorderStroke(1.dp, Color(android.graphics.Color.parseColor("#FFB2A5")))
                    ) {
                        Text("설정", color = Color.Black)
                    }
                }
            }
        }
    }
}
@Composable
fun Greeting(babyName: String) {
    androidx.wear.compose.material.Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 130.dp),
        textAlign = TextAlign.Center,
        color = Color(android.graphics.Color.BLACK),
        text = stringResource(R.string.babyName),
        // 텍스트의 두께를 조절하는 코드
        fontWeight = FontWeight.Bold
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen("babyHome")
}
