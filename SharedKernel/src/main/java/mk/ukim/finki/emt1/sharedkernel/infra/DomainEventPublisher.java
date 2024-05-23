package mk.ukim.finki.emt1.sharedkernel.infra;

import mk.ukim.finki.emt1.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
