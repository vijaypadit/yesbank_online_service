package com.yesbank.notification.repository;

import com.yesbank.notification.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog,Long> {

    List<NotificationLog> findByNotificationId(String notificationId);

}