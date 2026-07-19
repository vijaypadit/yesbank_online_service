package com.yesbank.notification.repository;

import com.yesbank.notification.entity.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate,Long> {

    Optional<NotificationTemplate> findByTemplateCode(String templateCode);

}