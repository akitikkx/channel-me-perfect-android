package com.example.channelmeperfect

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.example.channelmeperfect.ui.main.MainScreen
import com.example.channelmeperfect.ui.theme.ChannelMePerfectTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName

    private var isRationaleFlowDenied = mutableStateOf(false)

    private var isNotificationPermissionGranted = mutableStateOf(false)

    private var firebaseMessagingToken = mutableStateOf("")

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        isNotificationPermissionGranted.value = isGranted
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ChannelMePerfectTheme {
                MainScreen(
                    isNotificationPermissionGranted = isNotificationPermissionGranted.value,
                    isRationaleFlowDenied = isRationaleFlowDenied.value,
                    fcmToken = firebaseMessagingToken.value,
                    onRationaleFlowGranted = {
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    },
                    onRationaleFlowDenied = { isRationaleFlowDenied.value = true }
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        isNotificationPermissionGranted =
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )

        getCurrentFirebaseToken()
    }

    private fun getCurrentFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            firebaseMessagingToken.value = task.result
        })
    }
}
