package com.yesbank.notification.dto;

import com.yesbank.notification.enams.EventType;
import com.yesbank.notification.enams.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEventDTO {

    private String eventId;

    private EventType eventType;

    private Long bankId;

    private String customerName;

    private String email;

    private String mobileNumber;

    private NotificationType notificationType;

    private String accountNumber;

    private String message;

    private LocalDateTime eventTime;
}