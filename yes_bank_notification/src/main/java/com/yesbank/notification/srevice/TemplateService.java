package com.yesbank.notification.srevice;

import com.yesbank.notification.entity.NotificationTemplate;

public interface TemplateService {

    NotificationTemplate getTemplate(String templateCode);

}