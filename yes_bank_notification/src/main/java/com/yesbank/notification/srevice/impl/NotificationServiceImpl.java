package com.yesbank.notification.srevice.impl;

import com.yesbank.notification.dto.NotificationEventDTO;
import com.yesbank.notification.dto.NotificationRequestDTO;
import com.yesbank.notification.dto.NotificationResponseDTO;
import com.yesbank.notification.entity.Notification;
import com.yesbank.notification.entity.NotificationLog;
import com.yesbank.notification.enams.NotificationStatus;
import com.yesbank.notification.entity.NotificationTemplate;
import com.yesbank.notification.exception.InvalidNotificationException;
import com.yesbank.notification.exception.NotificationAlreadyExistsException;
import com.yesbank.notification.exception.NotificationNotFoundException;
import com.yesbank.notification.exception.NotificationSendException;
import com.yesbank.notification.mapper.NotificationMapper;
import com.yesbank.notification.repository.NotificationLogRepository;
import com.yesbank.notification.repository.NotificationRepository;
import com.yesbank.notification.repository.NotificationTemplateRepository;
import com.yesbank.notification.srevice.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository templateRepository;
    private final NotificationLogRepository logRepository;
    private final NotificationMapper notificationMapper;
    private final TemplateService templateService;
    private final EmailService emailService;
    private final SmsService smsService;
    private final NotificationLogService notificationLogService;


    private void validateNotificationRequest(NotificationRequestDTO request) {

        if (request == null) {
            throw new InvalidNotificationException("Notification request cannot be null.");
        }

        if (request.getBankId() == null) {
            throw new InvalidNotificationException("Bank Id is required.");
        }

        if (request.getCustomerName() == null || request.getCustomerName().isBlank()) {
            throw new InvalidNotificationException("Customer name is required.");
        }

        if (request.getNotificationType() == null) {
            throw new InvalidNotificationException("Notification type is required.");
        }

        if (request.getEventType() == null) {
            throw new InvalidNotificationException("Event type is required.");
        }

        if ((request.getEmail() == null || request.getEmail().isBlank()) && (request.getMobileNumber() == null || request.getMobileNumber().isBlank())) {

            throw new InvalidNotificationException("Either email or mobile number is required.");
        }
    }

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO request) {
        validateNotificationRequest(request);

        Notification notification = notificationMapper.toEntity(request);

        notificationRepository.save(notification);

        NotificationTemplate template = templateService.getTemplate(request.getEventType().name());

        notification.setMessage(template.getBody());

        emailService.sendEmail(notification);

        if (notification.getMobileNumber() != null && !notification.getMobileNumber().isBlank()) {

            smsService.sendSms(notification);

        }

        notification.setStatus(NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());

        notificationRepository.save(notification);

        notificationLogService.saveLog(notification, "200", "Notification Sent Successfully");

        return notificationMapper.toResponse(notification);
    }

    private void validateNotificationEvent(NotificationEventDTO event) {

        if (event == null) {
            throw new InvalidNotificationException("Notification event cannot be null.");
        }

        if (event.getEventId() == null || event.getEventId().isBlank()) {
            throw new InvalidNotificationException("Event Id is required.");
        }

        if (event.getEventType() == null) {
            throw new InvalidNotificationException("Event Type is required.");
        }

        if (event.getBankId() == null) {
            throw new InvalidNotificationException("Bank Id is required.");
        }
    }

    @Override
    public NotificationResponseDTO sendNotification(NotificationEventDTO event) {

        validateNotificationEvent(event);

        // Prevent duplicate processing
        notificationRepository.findByEventId(event.getEventId()).ifPresent(notification -> {
            throw new NotificationAlreadyExistsException("Notification already processed for Event Id : " + event.getEventId());
        });

        // Convert DTO -> Entity
        Notification notification = notificationMapper.toEntity(event);

        // Save as PENDING
        notification = notificationRepository.save(notification);

        try {

            // Send Email
            emailService.sendEmail(notification);

            // Send SMS (optional)
            if (notification.getMobileNumber() != null && !notification.getMobileNumber().isBlank()) {

                smsService.sendSms(notification);
            }

            // Update status after successful delivery
            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());
            notification.setUpdatedAt(LocalDateTime.now());

            notification = notificationRepository.save(notification);

            notificationLogService.saveLog(notification, "200", "Notification sent successfully");

        } catch (Exception ex) {

            notification.setStatus(NotificationStatus.FAILED);
            notification.setFailureReason(ex.getMessage());
            notification.setUpdatedAt(LocalDateTime.now());

            notificationRepository.save(notification);

            notificationLogService.saveLog(notification, "500", "Notification sending failed");

            throw new NotificationSendException("Unable to send notification");
        }

        return notificationMapper.toResponse(notification);
    }

    @Override
    public NotificationResponseDTO resendNotification(String notificationId) {

        Notification notification = notificationRepository.findByNotificationId(notificationId).orElseThrow(() -> new NotificationNotFoundException("Notification not found with Notification Id : " + notificationId));

        notification.setRetryCount(notification.getRetryCount() + 1);
        notification.setStatus(NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        notificationRepository.save(notification);

        saveLog(notification, "200", "Notification Resent");

        return mapResponse(notification);
    }

    @Override
    public List<NotificationResponseDTO> getAllNotifications() {

        return notificationRepository.findAll().stream().map(this::mapResponse).collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDTO getNotificationById(String notificationId) {

        Notification notification = notificationRepository.findByNotificationId(notificationId).orElseThrow(() -> new NotificationNotFoundException("Notification not found with Notification Id : " + notificationId));

        return mapResponse(notification);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByBankId(Long bankId) {

        return notificationRepository.findByBankId(bankId).stream().map(this::mapResponse).collect(Collectors.toList());
    }

    private NotificationResponseDTO mapResponse(Notification notification) {

        return NotificationResponseDTO.builder().notificationId(notification.getNotificationId()).status(notification.getStatus()).message("Success").build();
    }

    private void saveLog(Notification notification, String code, String responseMessage) {

        try {

            NotificationLog log = NotificationLog.builder().notificationId(notification.getNotificationId()).responseCode(code).responseMessage(responseMessage).status(notification.getStatus()).createdAt(LocalDateTime.now()).build();

            logRepository.save(log);

        } catch (Exception ex) {

            // log.error("Unable to save notification log", ex);

        }
    }
}