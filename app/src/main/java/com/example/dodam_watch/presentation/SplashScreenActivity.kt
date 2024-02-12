package com.example.dodam_watch.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.dodam_watch.presentation.theme.DoDam_watchTheme
import com.example.dodam_watch.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp("Android")
        }
        android.os.Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
            finish()
        }, 1000)
    }
}

@Composable
fun WearApp(greetingName: String) {
    DoDam_watchTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor("#FFB2A5"))), Alignment.Center
        ) {
            TimeText()
            Image(
                painter = painterResource(id = R.drawable.dodam_white), // 이미지 리소스 ID로 변경
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp) // 이미지 크기 조절
                    .align(Alignment.Center) // 가운데 정렬
                    .offset(y = (-16).dp) // 원하는 만큼 위로 조절
            )
            AppName(greetingName = greetingName) // 텍스트 출력
        }
    }
}

// 텍스트 관련 코드
@Composable
fun AppName(greetingName: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        textAlign = TextAlign.Center,
        color = Color.White,
        text = stringResource(R.string.MainTitle, greetingName),
        // 텍스트의 두께를 조절하는 코드
        fontWeight = FontWeight.Bold
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}