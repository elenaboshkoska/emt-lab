package mk.ukim.finki.emt1.guestmenagment.domain.ValueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt1.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Money;

@Getter
public class Event implements ValueObject {
    private final EventId id;
    private final String Name;
    private final Money price;
    private Event()
    {
        this.id = EventId.randomId(EventId.class);
        this.Name = "";
        this.price = Money.valueOf(Currency.EUR, 0);

    }

    @JsonCreator
    public Event(@JsonProperty("id") EventId id, @JsonProperty("productName") String Name,@JsonProperty("price") Money price)
    {
        this.id = id;
        this.Name = Name;
        this.price = price;

    }
}
