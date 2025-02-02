package ua.nure.kryvko.roman.Atark.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        try {
            Notification savedNotification = notificationService.createNotification(notification);
            return ResponseEntity.ok(savedNotification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Integer id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Notification> updateNotification(@PathVariable Integer id, @RequestBody Notification notification) {
        notification.setId(id);
        try {
            Notification updatedNotification = notificationService.updateNotification(notification);
            return ResponseEntity.ok(updatedNotification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer id) {
        try {
            notificationService.deleteNotificationById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
