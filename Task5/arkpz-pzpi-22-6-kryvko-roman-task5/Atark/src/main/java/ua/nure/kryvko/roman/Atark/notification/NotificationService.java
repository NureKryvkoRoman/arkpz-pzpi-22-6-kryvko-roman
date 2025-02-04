package ua.nure.kryvko.roman.Atark.notification;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.kryvko.roman.Atark.greenhouse.Greenhouse;
import ua.nure.kryvko.roman.Atark.greenhouse.GreenhouseRepository;
import ua.nure.kryvko.roman.Atark.user.User;
import ua.nure.kryvko.roman.Atark.user.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository,
                               GreenhouseRepository greenhouseRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.greenhouseRepository = greenhouseRepository;
    }

    @Transactional
    public Notification createNotification(Notification notification) {
        if (notification.getUser() == null || !userRepository.existsById(notification.getUser().getId())) {
            throw new IllegalArgumentException("User must exist to create a notification.");
        }

        User owner = userRepository.findById(notification.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User must exist to create a notification."));
        notification.setUser(owner);

        if (notification.getTimestamp() == null) {
            notification.setTimestamp(new Date());
        }

        return notificationRepository.save(notification);
    }

    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    @Transactional
    public Notification updateNotification(Notification notification) {
        if (notification.getId() == null || !notificationRepository.existsById(notification.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found for update");
        }

        User owner = userRepository.findById(notification.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User must exist to create a notification."));
        notification.setUser(owner);

        return notificationRepository.save(notification);
    }

    @Transactional
    public void deleteNotificationById(Integer id) {
        if (!notificationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found for update");
        }

        notificationRepository.deleteById(id);
    }

    public List<Notification> getNotificationsByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return notificationRepository.findAllByUser(user);
    }

    public List<Notification> getNotificationByUserId(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return notificationRepository.findAllByUser(user);
    }

    public List<Notification> getNotificationsByGreenhouse(Integer id) {
        Greenhouse greenhouse = greenhouseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Greenhouse not found"));
        return notificationRepository.findAllByGreenhouse(greenhouse);
    }

    public List<Notification> getUnreadNotificationsByUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return notificationRepository.findByUserAndIsReadFalse(user);
    }

    public List<Notification> getUnreadNotificationsByUserUrgency(Integer id, NotificationUrgency urgency) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return notificationRepository.findByUserAndNotificationUrgency(user, urgency);
    }
}
