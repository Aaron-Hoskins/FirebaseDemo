package com.examples.coding.firebasedemo

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotifyService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val message = remoteMessage.notification?.body
        val title = remoteMessage.notification?.title

        val notificationBuilder = NotificationCompat
            .Builder(this)
            .setContentTitle(title)
            .setContentText(message)
            .setChannelId("demo_channel")


        val notificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

    }
}
