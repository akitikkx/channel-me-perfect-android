package com.example.channelmeperfect.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.channelmeperfect.MainActivity
import com.example.channelmeperfect.R
import com.example.channelmeperfect.models.notifications.channels.NotificationChannels
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data = message.data
        val notifyChannel = data["channel_id"]?.let { NotificationChannels.fromString(it) }

        val title = data["title"] ?: message.notification?.title
        val body = data["body"] ?: message.notification?.body

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = createChannel(
            notifyChannel = notifyChannel,
            notificationManager = notificationManager
        )

        val clickAction = notifyChannel?.clickAction ?: MainActivity::class.java.toString()

        notify(
            channelId = channel.id,
            title = title,
            body = body,
            clickAction = clickAction,
            notificationManager = notificationManager
        )
    }

    private fun notify(
        channelId: String,
        title: String?,
        body: String?,
        clickAction: String,
        notificationManager: NotificationManager
    ) {
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    Random.nextInt(0x10000),
                    Intent(this, Class.forName(clickAction)),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(
        notifyChannel: NotificationChannels?,
        notificationManager: NotificationManager
    ): NotificationChannel {
        val channelId = notifyChannel?.name ?: getString(R.string.default_notification_channel_id)
        val channelName =
            notifyChannel?.channelName ?: getString(R.string.default_notification_channel_name)
        val channelDescription = notifyChannel?.channelDescription
            ?: getString(R.string.default_notification_channel_description)

        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = channelDescription
        notificationManager.createNotificationChannel(channel)

        return channel
    }
}