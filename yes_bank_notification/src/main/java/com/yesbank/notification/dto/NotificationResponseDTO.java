package com.yesbank.notification.dto;


import com.yesbank.notification.enams.NotificationStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {

    private String notificationId;

    private NotificationStatus status;

    private String message;
}