package mk.ukim.finki.emt1.guestmenagment.domain.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.ukim.finki.emt1.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class EventId extends DomainObjectId {

    private EventId(EventId id) {
        super(EventId.randomId(EventId.class).getId());
    }

    public EventId(@NonNull String uuid) {
        super(uuid);
    }

    public EventId() {
        super(EventId.randomId(EventId.class).getId());
    }
}
