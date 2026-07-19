package com.yesbank.notification.srevice.impl;

import com.yesbank.notification.entity.Notification;
import com.yesbank.notification.srevice.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendSms(Notification notification) {

        log.info("SMS sent to {}",
                notification.getMobileNumber());

    }

}