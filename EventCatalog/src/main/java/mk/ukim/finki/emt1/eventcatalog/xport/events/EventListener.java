package mk.ukim.finki.emt1.eventcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt1.eventcatalog.domain.models.EventId;
import mk.ukim.finki.emt1.eventcatalog.services.EventService;
import mk.ukim.finki.emt1.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt1.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt1.sharedkernel.domain.events.orders.GuestCreated;
import mk.ukim.finki.emt1.sharedkernel.domain.events.orders.GuestRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventListener {

    private final EventService productService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            GuestCreated event = DomainEvent.fromJson(jsonMessage,GuestCreated.class);
            productService.orderItemCreated(EventId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            GuestRemoved event = DomainEvent.fromJson(jsonMessage,GuestRemoved.class);
            productService.orderItemRemoved(EventId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }
}

