package com.yesbank.notification.srevice;

import com.yesbank.notification.dto.NotificationEventDTO;
import com.yesbank.notification.dto.NotificationRequestDTO;
import com.yesbank.notification.dto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO request);

    NotificationResponseDTO sendNotification(NotificationEventDTO event);

    NotificationResponseDTO resendNotification(String notificationId);

    List<NotificationResponseDTO> getAllNotifications();

    NotificationResponseDTO getNotificationById(String notificationId);

    List<NotificationResponseDTO> getNotificationsByBankId(Long bankId);

}
