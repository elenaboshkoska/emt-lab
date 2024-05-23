package mk.ukim.finki.emt1.guestmenagment.infra;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt1.sharedkernel.domain.events.DomainEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DomainEventPublishImpl {
    private final KafkaTemplate<String,String> kafkaTemplate;

    
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());
    }

}
