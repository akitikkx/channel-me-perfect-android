package com.example.channelmeperfect.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.channelmeperfect.R
import com.example.channelmeperfect.ui.theme.ChannelMePerfectTheme

@Composable
fun MainScreen(
    isNotificationPermissionGranted: Boolean,
    isRationaleFlowDenied: Boolean,
    onRationaleFlowGranted: () -> Unit,
    onRationaleFlowDenied: () -> Unit,
    fcmToken: String? = null,
    modifier: Modifier = Modifier
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your notification icon
            contentDescription = "Notification Icon",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        NotificationPermissionStatus(
            isNotificationPermissionGranted = isNotificationPermissionGranted,
            isRationaleFlowDenied = isRationaleFlowDenied
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (!isNotificationPermissionGranted && !isRationaleFlowDenied) {
            NotificationRationale(
                onRationaleFlowGranted = { onRationaleFlowGranted() },
                onRationaleFlowDenied = { onRationaleFlowDenied() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (!fcmToken.isNullOrEmpty()) {
            TokenArea(fcmToken) { text ->
                clipboardManager.setText(AnnotatedString(text))
            }
        }
    }
}

@Composable
fun NotificationPermissionStatus(
    isNotificationPermissionGranted: Boolean,
    isRationaleFlowDenied: Boolean,
) {
    if (isNotificationPermissionGranted) {
        Text(
            text = "Notification permission has been granted.",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }

    if (isRationaleFlowDenied) {
        Text(
            text = "Notification permission has been denied. Please enable in settings.",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NotificationRationale(
    onRationaleFlowGranted: () -> Unit,
    onRationaleFlowDenied: () -> Unit,
) {
    Text(
        text = "Enable Notifications",
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Allow this app to send you important notifications about:",
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "- New messages and updates",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )

    Text(
        text = "- Reminders and alerts",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )

    Text(
        text = "- Time-sensitive information",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(16.dp))

    RationaleButtons(
        onRationaleFlowGranted = { onRationaleFlowGranted() },
        onRationaleFlowDenied = { onRationaleFlowDenied() }
    )
}

@Composable
fun RationaleButtons(
    onRationaleFlowGranted: () -> Unit,
    onRationaleFlowDenied: () -> Unit,
) {
    Button(
        onClick = {
            onRationaleFlowGranted()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Grant Permissions")
    }

    Button(
        onClick = {
            onRationaleFlowDenied()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("No Thanks")
    }
}

@Composable
fun TokenArea(token: String, onCopy: (String) -> Unit) {
    SelectionContainer {
        Text(
            text = "Your FCM Token: $token",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Button(
        onClick = { onCopy(token) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Copy Token")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPermissionNotGrantedPreview() {
    ChannelMePerfectTheme {
        MainScreen(
            isNotificationPermissionGranted = false,
            isRationaleFlowDenied = false,
            onRationaleFlowGranted = {},
            onRationaleFlowDenied = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPermissionGrantedPreview() {
    ChannelMePerfectTheme {
        MainScreen(
            isNotificationPermissionGranted = true,
            isRationaleFlowDenied = false,
            onRationaleFlowGranted = {},
            onRationaleFlowDenied = {},
            fcmToken = "Some very long text will go here"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenRationaleFlowDeniedPreview() {
    ChannelMePerfectTheme {
        MainScreen(
            isNotificationPermissionGranted = false,
            isRationaleFlowDenied = true,
            onRationaleFlowGranted = {},
            onRationaleFlowDenied = {}
        )
    }
}