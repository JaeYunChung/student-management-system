package com.example.studentmanagementsystem.firebase.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;


@Service
public class FcmService {
    public String sendNotification(String title, String body, String token) throws Exception{
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();
        return FirebaseMessaging.getInstance().send(message);
    }
}
