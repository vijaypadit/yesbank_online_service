package com.yesbank.notification.srevice.impl;

import com.yesbank.notification.entity.Notification;
import com.yesbank.notification.srevice.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(Notification notification) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(notification.getEmail());

        mail.setSubject("YesBank Notification");

        mail.setText(notification.getMessage());

        mailSender.send(mail);

        log.info("Email sent to {}", notification.getEmail());

    }

}