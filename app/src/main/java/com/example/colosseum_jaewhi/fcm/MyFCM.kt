package com.example.colosseum_jaewhi.fcm

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        // Handler(android.os)
        val myHandler = Handler(Looper.getMainLooper())
        // 메세지를 받으면 toast를 띄워줄께~
        myHandler.post {
        // notification은 무조건 null이 아니라고 해두자.
            Toast.makeText(applicationContext, p0.notification!!.title, Toast.LENGTH_SHORT).show()

        }

    }

}