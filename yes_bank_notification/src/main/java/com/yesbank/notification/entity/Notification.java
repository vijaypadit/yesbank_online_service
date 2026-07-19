package com.yesbank.notification.entity;


import com.yesbank.notification.enams.EventType;
import com.yesbank.notification.enams.NotificationStatus;
import com.yesbank.notification.enams.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String notificationId;

    @Column(nullable = false)
    private String eventId;

    @Column(nullable = false)
    private Long bankId;

    private String customerName;

    private String accountNumber;

    private String email;

    private String mobileNumber;

    private String subject;

    private String provider;

    @Column(length = 5000)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private Integer retryCount;

    private String failureReason;

    private LocalDateTime sentAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}