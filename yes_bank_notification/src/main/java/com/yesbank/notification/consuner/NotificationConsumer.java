package com.yesbank.notification.consuner;

import com.yesbank.notification.dto.NotificationEventDTO;
import com.yesbank.notification.srevice.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "user-created",
            groupId = "notification-group")
    public void consumeUserCreated(NotificationEventDTO event){

        log.info("Received User Created Event : {}",event.getEventId());

        notificationService.sendNotification(event);

    }

    @KafkaListener(
            topics = "money-transfer",
            groupId = "notification-group")
    public void consumeMoneyTransfer(NotificationEventDTO event){

        log.info("Received Money Transfer Event : {}",event.getEventId());

        notificationService.sendNotification(event);

    }

    @KafkaListener(
            topics = "card-created",
            groupId = "notification-group")
    public void consumeCardCreated(NotificationEventDTO event){

        notificationService.sendNotification(event);

    }

    @KafkaListener(
            topics = "password-reset",
            groupId = "notification-group")
    public void consumePasswordReset(NotificationEventDTO event){

        notificationService.sendNotification(event);

    }

}