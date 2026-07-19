package com.yesbank.notification.producer;

import com.yesbank.notification.dto.NotificationEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationEventDTO> kafkaTemplate;

    @Value("${notification.kafka.topic}")
    private String topic;

    public void publishNotification(NotificationEventDTO event) {

        kafkaTemplate.send(topic, event.getEventId(), event);

    }

}