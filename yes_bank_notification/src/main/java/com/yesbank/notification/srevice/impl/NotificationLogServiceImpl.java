package com.yesbank.notification.srevice.impl;

import com.yesbank.notification.entity.Notification;
import com.yesbank.notification.entity.NotificationLog;
import com.yesbank.notification.repository.NotificationLogRepository;
import com.yesbank.notification.srevice.NotificationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationLogServiceImpl implements NotificationLogService {

    private final NotificationLogRepository repository;

    @Override
    public void saveLog(Notification notification, String code, String message) {

        NotificationLog log = NotificationLog.builder()
                .notificationId(notification.getNotificationId())
                .responseCode(code)
                .responseMessage(message)
                .status(notification.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(log);

    }

}