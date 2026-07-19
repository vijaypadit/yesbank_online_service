package com.yesbank.notification.srevice;



import com.yesbank.notification.entity.Notification;

public interface SmsService {

    void sendSms(Notification notification);

}