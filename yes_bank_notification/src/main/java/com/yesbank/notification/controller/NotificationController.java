package com.yesbank.notification.controller;

import com.yesbank.notification.dto.NotificationRequestDTO;
import com.yesbank.notification.dto.NotificationResponseDTO;
import com.yesbank.notification.srevice.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Admin/Test API
     * Creates a notification manually.
     */
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(
            @RequestBody NotificationRequestDTO request) {

        NotificationResponseDTO response =
                notificationService.createNotification(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Admin API
     * Fetch all notifications.
     */
    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotifications() {

        return ResponseEntity.ok(
                notificationService.getAllNotifications());
    }

    /**
     * Admin API
     * Fetch notification by Notification Id.
     */
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponseDTO> getNotificationById(
            @PathVariable String notificationId) {

        return ResponseEntity.ok(
                notificationService.getNotificationById(notificationId));
    }

    /**
     * Admin API
     * Fetch notifications by Bank Id.
     */
    @GetMapping("/bank/{bankId}")
    public ResponseEntity<List<NotificationResponseDTO>>
    getNotificationsByBankId(@PathVariable Long bankId) {

        return ResponseEntity.ok(
                notificationService.getNotificationsByBankId(bankId));
    }

    /**
     * Admin API
     * Retry failed notification.
     */
    @PutMapping("/{notificationId}/resend")
    public ResponseEntity<NotificationResponseDTO> resendNotification(
            @PathVariable String notificationId) {

        return ResponseEntity.ok(
                notificationService.resendNotification(notificationId));
    }

}