package com.example.channelmeperfect.services

import com.google.firebase.messaging.FirebaseMessagingService

class MessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}