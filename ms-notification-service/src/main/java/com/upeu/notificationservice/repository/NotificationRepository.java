// Archivo: src/main/java/com/upeu/notificationservice/repository/NotificationRepository.java
package com.upeu.notificationservice.repository;

import com.upeu.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByNotificationCode(String notificationCode);
}
