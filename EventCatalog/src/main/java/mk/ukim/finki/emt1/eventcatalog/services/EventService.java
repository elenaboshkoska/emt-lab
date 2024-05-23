package mk.ukim.finki.emt1.eventcatalog.services;

import mk.ukim.finki.emt1.eventcatalog.domain.models.EventId;
import mk.ukim.finki.emt1.eventcatalog.domain.models.Event;
import mk.ukim.finki.emt1.eventcatalog.services.forms.EventForm;

import java.util.List;

public interface EventService {
    Event findById(EventId id);
    Event createProduct(EventForm form);
    Event orderItemCreated(EventId productId, int quantity);
    Event orderItemRemoved(EventId productId, int quantity);
    List<Event> getAll();
}
