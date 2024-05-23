package mk.ukim.finki.emt1.eventcatalog.domain.models;


import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt1.sharedkernel.domain.base.DomainObjectId;
@Getter
public class EventId extends DomainObjectId {
    private EventId() {
        super(EventId.randomId(EventId.class).getId());
    }

    public EventId(@NonNull String uuid) {
        super(uuid);
    }

    public static EventId of(String uuid) {
        EventId p = new EventId(uuid);
        return p;
    }

}