package mk.ukim.finki.emt1.eventcatalog.domain.models;

import jakarta.persistence.*;
import mk.ukim.finki.emt1.eventcatalog.domain.models.ValueObjects.Quantity;
import mk.ukim.finki.emt1.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Money;

@Entity
@Table(name="eventModel")
public class Event extends AbstractEntity<EventId> {
    private String Name;
    @AttributeOverrides( {
            @AttributeOverride(name="amount", column=@Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money price;
    private int sales =0;

    public Event()
    {
        super(EventId.randomId(EventId.class));
    }

    //kolku prodazhbi inicijalno imal
    public static Event build(String Name, Money price, int sales)
    {
        Event e = new Event();
        e.Name = Name;
        e.price = price;
        e.sales = sales;
        return e;
    }
    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }

}