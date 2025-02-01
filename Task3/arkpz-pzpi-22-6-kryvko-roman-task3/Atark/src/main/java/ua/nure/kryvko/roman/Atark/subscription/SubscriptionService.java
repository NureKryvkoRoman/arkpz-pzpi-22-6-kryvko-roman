package ua.nure.kryvko.roman.Atark.subscription;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription saveSubscription(Subscription subscription) {
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
