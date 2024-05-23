package mk.ukim.finki.emt1.guestmenagment.domain.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt1.guestmenagment.domain.ValueObject.EventId;
import mk.ukim.finki.emt1.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Money;

import java.time.Instant;

@Entity
@Table(name="registration")
@Getter
public class Registration extends AbstractEntity<RegistrationId> {
    private Money registrationPrice;
    @Column(name="time", nullable = false)
    private Instant time;
    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name="id", column = @Column(name="event_id", nullable = false))
    private EventId eventId;

    public Registration(@NonNull EventId eventId, @NonNull Money money)
    {
        this.eventId = eventId;
        this.registrationPrice = money;
    }
    public Registration(@NonNull EventId eventId, @NonNull Money money, int quantity)
    {
        this.eventId = eventId;
        this.registrationPrice = money;
        this.quantity = quantity;
    }


    public Registration() {
    }
    public Money subtotal() {
        return registrationPrice.multiply(quantity);
    }
}
