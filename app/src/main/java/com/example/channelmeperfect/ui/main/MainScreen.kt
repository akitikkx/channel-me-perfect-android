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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.channelmeperfect.R
import com.example.channelmeperfect.ui.theme.ChannelMePerfectTheme

@Composable
fun NotificationPermissionRationale() {
    Column(
        modifier = Modifier
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

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle user's decision to grant permissions */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Grant Permissions")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationPermissionRationalePreview() {
    ChannelMePerfectTheme {
        NotificationPermissionRationale()
    }
}