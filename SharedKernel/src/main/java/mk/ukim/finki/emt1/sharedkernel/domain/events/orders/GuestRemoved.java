package mk.ukim.finki.emt1.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt1.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt1.sharedkernel.domain.events.DomainEvent;

@Getter
public class GuestRemoved extends DomainEvent {
    private String productId;
    private int quantity;

    public GuestRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public GuestRemoved(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.quantity = quantity;
    }

}
