package mk.ukim.finki.emt1.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt1.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt1.sharedkernel.domain.events.DomainEvent;
@Getter
public class GuestCreated extends DomainEvent {

    private String productId;
    private int quantity;

    public GuestCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public GuestCreated(String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.productId = productId;
        this.quantity = quantity;
    }

}
