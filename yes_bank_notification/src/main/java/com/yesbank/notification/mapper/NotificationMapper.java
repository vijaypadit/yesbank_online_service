package com.yesbank.notification.mapper;

import com.yesbank.notification.dto.NotificationEventDTO;
import com.yesbank.notification.dto.NotificationRequestDTO;
import com.yesbank.notification.dto.NotificationResponseDTO;
import com.yesbank.notification.entity.Notification;

public interface NotificationMapper {

    Notification toEntity(NotificationRequestDTO request);

    Notification toEntity(NotificationEventDTO event);

    NotificationResponseDTO toResponse(Notification notification);

}