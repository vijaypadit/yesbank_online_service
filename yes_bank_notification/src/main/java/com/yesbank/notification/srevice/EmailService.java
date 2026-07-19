package com.yesbank.notification.srevice;



import com.yesbank.notification.entity.Notification;

public interface EmailService {

    void sendEmail(Notification notification);

}