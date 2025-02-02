package ua.nure.kryvko.roman.Atark.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription savedSubscription = subscriptionService.saveSubscription(subscription);
        return ResponseEntity.ok(savedSubscription);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Integer id) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
        return subscription.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Integer id, @RequestBody Subscription subscription) {
        subscription.setId(id);
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);
        return ResponseEntity.ok(updatedSubscription);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Integer id) {
        subscriptionService.deleteSubscriptionById(id);
        return ResponseEntity.noContent().build();
    }
}
