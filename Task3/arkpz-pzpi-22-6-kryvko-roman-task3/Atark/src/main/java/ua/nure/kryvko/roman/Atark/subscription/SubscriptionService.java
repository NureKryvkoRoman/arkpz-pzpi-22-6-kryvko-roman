package ua.nure.kryvko.roman.Atark.subscription;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.kryvko.roman.Atark.user.User;
import ua.nure.kryvko.roman.Atark.user.UserRepository;

import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Subscription saveSubscription(Subscription subscription) {
        User owner = userRepository.findById(subscription.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure the user is a managed entity
        subscription.setUser(owner);
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscriptionById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    public void deleteSubscriptionById(Integer id) {
        subscriptionRepository.deleteById(id);
    }

    public Subscription updateSubscription(Subscription subscription) {
        if (subscription.getId() == null || !subscriptionRepository.existsById(subscription.getId())) {
            throw new IllegalArgumentException("Subscription ID not found for update.");
        }
        return subscriptionRepository.save(subscription);
    }
}
