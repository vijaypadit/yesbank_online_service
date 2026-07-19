package com.yesbank.notification.dto;


import com.yesbank.notification.enams.EventType;
import com.yesbank.notification.enams.NotificationType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDTO {

    private Long bankId;

    private String customerName;

    private String email;

    private String mobileNumber;

    private String accountNumber;

    private String message;

    private NotificationType notificationType;

    private EventType eventType;
}