package mk.ukim.finki.emt1.guestmenagment.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt1.guestmenagment.domain.ValueObject.Event;
import mk.ukim.finki.emt1.guestmenagment.service.forms.GuestForm;
import mk.ukim.finki.emt1.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Money;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Min;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="guests")
@Getter
public class Guest  extends AbstractEntity<GuestId> {
    private String name;
    private String surname;
    private Integer years;
    private Instant time;
    //site promeni vo Registracija kje se pravat preku ovaa klasa, nikako preku samiot registration
    // eager za da mora da se hendla vo edna transakcija
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Registration> SignIn;
    public Guest(Instant time, @NotNull Currency cur)
    {
        super(GuestId.randomId(GuestId.class));
    }

    public Guest() {
        super(GuestId.randomId(GuestId.class));
    }
    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Registration addGuest(Event event, @Min(1) int quantity)
    {
        Objects.requireNonNull(event, "event must not be null");
        var item = new Registration(event.getId(), event.getPrice());
        SignIn.add(item);
        return  item;
    }
    public void removeGuest(RegistrationId registrationId)
    {
        Objects.requireNonNull(registrationId, "Registration Item must not be null");
        SignIn.removeIf(v->v.getId().equals(registrationId));
    }
    public Money total() {
        return SignIn.stream().map(Registration::subtotal).reduce(new Money(currency, 0), Money::add);
    }

}
