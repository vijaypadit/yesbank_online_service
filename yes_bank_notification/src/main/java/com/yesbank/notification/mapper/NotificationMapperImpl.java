package com.yesbank.notification.mapper;

import com.yesbank.notification.dto.NotificationEventDTO;
import com.yesbank.notification.dto.NotificationRequestDTO;
import com.yesbank.notification.dto.NotificationResponseDTO;
import com.yesbank.notification.entity.Notification;
import com.yesbank.notification.enams.NotificationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification toEntity(NotificationRequestDTO request) {

        return Notification.builder()
                .notificationId(UUID.randomUUID().toString())
                .eventId(UUID.randomUUID().toString())
                .bankId(request.getBankId())
                .customerName(request.getCustomerName())
                .accountNumber(request.getAccountNumber())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .message(request.getMessage())
                .notificationType(request.getNotificationType())
                .eventType(request.getEventType())
                .status(NotificationStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public Notification toEntity(NotificationEventDTO event) {

        return Notification.builder()
                .notificationId(UUID.randomUUID().toString())
                .eventId(event.getEventId())
                .bankId(event.getBankId())
                .customerName(event.getCustomerName())
                .accountNumber(event.getAccountNumber())
                .email(event.getEmail())
                .mobileNumber(event.getMobileNumber())
                .message(event.getMessage())
                .notificationType(event.getNotificationType())
                .eventType(event.getEventType())
                .status(NotificationStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public NotificationResponseDTO toResponse(Notification notification) {

        return NotificationResponseDTO.builder()
                .notificationId(notification.getNotificationId())
                .status(notification.getStatus())
                .message(notification.getStatus().name())
                .build();
    }

}