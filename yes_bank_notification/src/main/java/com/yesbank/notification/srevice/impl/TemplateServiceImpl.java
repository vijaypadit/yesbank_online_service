package com.yesbank.notification.srevice.impl;

import com.yesbank.notification.entity.NotificationTemplate;
import com.yesbank.notification.exception.NotificationNotFoundException;
import com.yesbank.notification.repository.NotificationTemplateRepository;
import com.yesbank.notification.srevice.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final NotificationTemplateRepository repository;

    @Override
    public NotificationTemplate getTemplate(String templateCode) {

        return repository.findByTemplateCode(templateCode).orElseThrow(() -> new NotificationNotFoundException("Template not found"));

    }

}