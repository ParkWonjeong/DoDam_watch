// LoginActivity.kt

package com.example.dodam_watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dodam_watch.R
import com.example.dodam_watch.presentation.theme.DoDam_watchTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    DoDam_watchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor("#FFB2A5"))),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 상단 제목
                Text(
                    text = "계정 연동",
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                // 특정 코드 입력 칸
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* 아무것도 하지 않음 */ },
                    label = { Text("유저 코드 입력") },
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .background(Color(android.graphics.Color.WHITE)),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                )

                // 확인 버튼
                Button(
                    onClick = { /* 버튼 클릭 시 동작 */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.WHITE)),
                    shape = MaterialTheme.shapes.small,
                    border = BorderStroke(2.dp, Color(android.graphics.Color.parseColor("#D9D9D9")))
                ) {
                    Text("확인", color = Color.Black)
                }
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
