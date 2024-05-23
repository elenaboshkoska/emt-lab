package mk.ukim.finki.emt1.eventcatalog.domain.models.ValueObjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Quantity {
    private final int quantity;
    protected Quantity()
    {
        this.quantity =0;
    }
}
