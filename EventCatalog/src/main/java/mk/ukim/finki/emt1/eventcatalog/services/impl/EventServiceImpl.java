package mk.ukim.finki.emt1.eventcatalog.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt1.eventcatalog.domain.exceptions.IdDoesNotExist;
import mk.ukim.finki.emt1.eventcatalog.domain.models.EventId;
import mk.ukim.finki.emt1.eventcatalog.domain.models.Event;
import mk.ukim.finki.emt1.eventcatalog.domain.repository.EventRepository;
import mk.ukim.finki.emt1.eventcatalog.services.EventService;
import mk.ukim.finki.emt1.eventcatalog.services.forms.EventForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    public Event findById(EventId id) {
       // return this.eventRepository.findById(id).orElseThrow(IdDoesNotExist::new);
        return this.eventRepository.findById(id).orElseThrow(IdDoesNotExist::new);
    }

    @Override
    public Event createProduct(EventForm form) {
        Event p = Event.build(form.getProductName(),form.getPrice(),form.getSales());
        eventRepository.save(p);
        return p;


    }

    @Override
    public Event orderItemCreated(EventId productId, int quantity) {
        Event p = eventRepository.findById(productId).orElseThrow(IdDoesNotExist::new);
        p.addSales(quantity);
        eventRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public Event orderItemRemoved(EventId productId, int quantity) {
            Event p = eventRepository.findById(productId).orElseThrow(IdDoesNotExist::new);
            p.removeSales(quantity);
            eventRepository.saveAndFlush(p);
            return p;

        }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }
}
