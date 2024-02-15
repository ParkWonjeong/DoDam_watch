package com.example.dodam_watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.OnDataPointListener
import com.google.android.gms.fitness.request.SensorRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit

class HeartRateMeasurementActivity : ComponentActivity() {

    private var onDataPointListener: OnDataPointListener? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HeartRateMeasurementScreen()
        }
    }

    @Composable
    fun HeartRateMeasurementScreen() {
        var heartRate by remember { mutableStateOf("") }
        var isMeasuring by remember { mutableStateOf(false) }

        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = heartRate,
                onValueChange = { heartRate = it },
                label = { Text("심박수 (bpm)") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 16.dp)
            )

            Button(
                onClick = {
                    if (isMeasuring) {
                        stopMeasurement()
                    } else {
                        startMeasurement()
                    }
                    isMeasuring = !isMeasuring
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#FFB2A5")))
            ) {
                Text(if (isMeasuring) "측정 종료" else "측정 시작")
            }
        }
    }

    // 나머지 부분을 여기에 추가 하세요.
    // ...
    private val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1234
    private fun startMeasurement() {
        // Fit API 권한 확인
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_WRITE)
            .build()

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                this,
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                account,
                fitnessOptions
            )
        } else {
            // 권한이 이미 부여된 경우 심박수 측정 시작
            measureHeartRate()
        }
    }
    val fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_WRITE)
        .build()

    private fun measureHeartRate() {

        onDataPointListener = OnDataPointListener { dataPoint ->
            for (field in dataPoint.dataType.fields) {
                val value = dataPoint.getValue(field)
                if (field.name == Field.FIELD_BPM.name) {
                    // heart rate
                    // 여기에서 심박수 값을 사용하여 UI에 표시하거나 다른 작업을 수행
                }
            }
        }

        // Fit API를 사용하여 심박수 데이터를 실시간으로 받아옵니다.
        Fitness.getSensorsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .add(
                SensorRequest.Builder()
                    .setDataType(DataType.TYPE_HEART_RATE_BPM)
                    .setSamplingRate(3, TimeUnit.SECONDS)
                    .build(),
                onDataPointListener!!
            )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // 성공적으로 등록되었을 때 수행할 작업
                } else {
                    // 실패한 경우 처리
                }
            }
    }

    private fun stopMeasurement() {
        // 심박수 측정 중지
        onDataPointListener?.let {
            Fitness.getSensorsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
                .remove(it)
        }
    }
}
