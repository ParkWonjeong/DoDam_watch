//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.fitness.Fitness
//import com.google.android.gms.fitness.FitnessOptions
//import com.google.android.gms.fitness.data.DataType
//import com.google.android.gms.fitness.data.Field
//import com.google.android.gms.fitness.data.Value
//import com.google.android.gms.fitness.request.OnDataPointListener
//import com.google.android.gms.fitness.request.SensorRequest
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.concurrent.TimeUnit
//
//class BloodPressureMeasurementActivity : ComponentActivity() {
//
//    private var onDataPointListener: OnDataPointListener? = null
//    private val coroutineScope = CoroutineScope(Dispatchers.Default)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            BloodPressureMeasurementScreen()
//        }
//    }
//
//    @Composable
//    fun BloodPressureMeasurementScreen() {
//        var systolicPressure by remember { mutableStateOf("") }
//        var diastolicPressure by remember { mutableStateOf("") }
//        var isMeasuring by remember { mutableStateOf(false) }
//
//        val keyboardController = LocalSoftwareKeyboardController.current
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            OutlinedTextField(
//                value = systolicPressure,
//                onValueChange = { systolicPressure = it },
//                label = { Text("Systolic Pressure (mmHg)") },
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    }
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//
//            OutlinedTextField(
//                value = diastolicPressure,
//                onValueChange = { diastolicPressure = it },
//                label = { Text("Diastolic Pressure (mmHg)") },
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    }
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//
//            Button(
//                onClick = {
//                    if (isMeasuring) {
//                        stopMeasurement()
//                    } else {
//                        startMeasurement()
//                    }
//                    isMeasuring = !isMeasuring
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(if (isMeasuring) "Stop Measurement" else "Start Measurement")
//            }
//        }
//    }
//
//    private fun startMeasurement() {
//        // Fit API 권한 확인
//        val fitnessOptions = FitnessOptions.builder()
//            .addDataType(DataType.TYPE_BLOOD_PRESSURE, FitnessOptions.ACCESS_WRITE)
//            .build()
//
//        val account = GoogleSignIn.getAccountForExtension(this, fitnessOptions)
//
//        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
//            GoogleSignIn.requestPermissions(
//                this,
//                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
//                account,
//                fitnessOptions
//            )
//        } else {
//            // 권한이 이미 부여된 경우 혈압 측정 시작
//            measureBloodPressure()
//        }
//    }
//
//    private fun measureBloodPressure() {
//        onDataPointListener = OnDataPointListener { dataPoint ->
//            for (field in dataPoint.dataType.fields) {
//                val value = dataPoint.getValue(field)
//                if (field.name == Field.FIELD_BLOOD_PRESSURE_SYSTOLIC.name) {
//                    // systolic pressure
//                    // 여기에서 systolic pressure 값을 사용하여 UI에 표시하거나 다른 작업을 수행
//                } else if (field.name == Field.FIELD_BLOOD_PRESSURE_DIASTOLIC.name) {
//                    // diastolic pressure
//                    // 여기에서 diastolic pressure 값을 사용하여 UI에 표시하거나 다른 작업을 수행
//                }
//            }
//        }
//
//        // Fit API를 사용하여 혈압 데이터를 실시간으로 받아옵니다.
//        Fitness.getSensorsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
//            .add(
//                SensorRequest.Builder()
//                    .setDataType(DataType.TYPE_BLOOD_PRESSURE)
//                    .setSamplingRate(3, TimeUnit.SECONDS)
//                    .build(),
//                onDataPointListener
//            )
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {                    // 센서가 추가되었습니다.
//                    // 혈압 측정 시작 메시지를 UI에 표시하거나 다른 작업을 수행
//                } else {
//                    // 센서 추가 실패
//                    // 실패 메시지를 UI에 표시하거나 다른 작업을 수행
//                }
//            }
//
//        // 혈압 데이터 수집을 위한 리스너 등록
//        Fitness.getSensorsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
//            .add(
//                SensorRequest.Builder()
//                    .setDataType(DataType.TYPE_BLOOD_PRESSURE)
//                    .setSamplingRate(3, TimeUnit.SECONDS)
//                    .build(),
//                onDataPointListener
//            )
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // 센서가 추가되었습니다.
//                    // 혈압 측정 시작 메시지를 UI에 표시하거나 다른 작업을 수행
//                } else {
//                    // 센서 추가 실패
//                    // 실패 메시지를 UI에 표시하거나 다른 작업을 수행
//                }
//            }
//    }
//
//    private fun stopMeasurement() {
//        // 혈압 데이터 수집 중단
//        Fitness.getSensorsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
//            .remove(onDataPointListener)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // 센서가 제거되었습니다.
//                    // 혈압 측정 중단 메시지를 UI에 표시하거나 다른 작업을 수행
//                } else {
//                    // 센서 제거 실패
//                    // 실패 메시지를 UI에 표시하거나 다른 작업을 수행
//                }
//            }
//    }
//
//    companion object {
//        private const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1001
//    }
//}
//
