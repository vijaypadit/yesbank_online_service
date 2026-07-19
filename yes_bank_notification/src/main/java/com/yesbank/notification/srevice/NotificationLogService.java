package com.yesbank.notification.srevice;

import com.yesbank.notification.entity.Notification;

public interface NotificationLogService {

    void saveLog(Notification notification,
                 String code,
                 String message);

}