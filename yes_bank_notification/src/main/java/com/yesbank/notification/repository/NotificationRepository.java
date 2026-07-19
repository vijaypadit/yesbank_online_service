package com.yesbank.notification.repository;


import com.yesbank.notification.entity.Notification;
import com.yesbank.notification.enams.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByNotificationId(String notificationId);

    List<Notification> findByBankId(Long bankId);

    List<Notification> findByEmail(String email);

    List<Notification> findByNotificationType(String notificationType);

    List<Notification> findByStatus(NotificationStatus status);

    List<Notification> findByRetryCountLessThan(Integer retryCount);

    Optional<Notification> findByEventId(String eventId);




}