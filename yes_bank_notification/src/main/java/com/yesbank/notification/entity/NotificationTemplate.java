package com.yesbank.notification.entity;


import com.yesbank.notification.enams.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateCode;

    private String templateName;

    private String subject;

    @Column(length = 5000)
    private String body;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}