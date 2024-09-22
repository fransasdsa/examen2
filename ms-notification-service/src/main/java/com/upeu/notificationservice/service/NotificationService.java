// Archivo: src/main/java/com/upeu/notificationservice/service/NotificationService.java
package com.upeu.notificationservice.service;

import com.upeu.notificationservice.entity.Notification;
import com.upeu.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Optional<Notification> getNotificationByCode(String notificationCode) {
        return notificationRepository.findByNotificationCode(notificationCode);
    }

    public Notification createNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);
        sendEmailNotification(savedNotification);
        return savedNotification;
    }

    public Notification updateNotification(Long id, Notification notificationDetails) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setNotificationCode(notificationDetails.getNotificationCode());
            notification.setMessage(notificationDetails.getMessage());
            notification.setEmail(notificationDetails.getEmail());
            notification.setType(notificationDetails.getType());
            // No actualizar timestamp en actualización
            Notification updatedNotification = notificationRepository.save(notification);
            sendEmailNotification(updatedNotification);
            return updatedNotification;
        }).orElseGet(() -> {
            notificationDetails.setId(id);
            notificationDetails.setTimestamp(LocalDateTime.now());
            Notification savedNotification = notificationRepository.save(notificationDetails);
            sendEmailNotification(savedNotification);
            return savedNotification;
        });
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    private void sendEmailNotification(Notification notification) {
        if ("EMAIL".equalsIgnoreCase(notification.getType())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getEmail());
            message.setSubject("Nueva Notificación: " + notification.getNotificationCode());
            message.setText(notification.getMessage());
            mailSender.send(message);
        }
        // Implementar otros tipos de notificaciones como SMS si es necesario
    }
}
