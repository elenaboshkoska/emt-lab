package mk.ukim.finki.emt1.guestmenagment.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt1.sharedkernel.domain.base.DomainObjectId;

public class GuestId extends DomainObjectId {
    private GuestId() {
        super(GuestId.randomId(GuestId.class).getId());
    }

    public GuestId(@NonNull String uuid) {
        super(uuid);
    }
}
