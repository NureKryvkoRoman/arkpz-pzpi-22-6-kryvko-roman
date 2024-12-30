package ua.nure.kryvko.roman.Atark.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.nure.kryvko.roman.Atark.user.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification createNotification(Notification notification) {
        if (notification.getUser() == null || !userRepository.existsById(notification.getUser().getId())) {
            throw new IllegalArgumentException("User must exist to create a notification.");
        }

        if (notification.getTimestamp() == null) {
            notification.setTimestamp(new Date());
        }

        return notificationRepository.save(notification);
    }

    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    public Notification updateNotification(Notification notification) {
        if (notification.getId() == null || !notificationRepository.existsById(notification.getId())) {
            throw new IllegalArgumentException("Notification ID not found for update.");
        }

        return notificationRepository.save(notification);
    }

    public void deleteNotificationById(Integer id) {
        try {
            notificationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Notification ID not found for deletion.");
        }
    }
}
