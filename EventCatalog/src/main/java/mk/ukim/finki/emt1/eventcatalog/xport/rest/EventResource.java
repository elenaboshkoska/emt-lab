package mk.ukim.finki.emt1.eventcatalog.xport.rest;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt1.eventcatalog.domain.models.Event;
import mk.ukim.finki.emt1.eventcatalog.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor

public class EventResource {
    private final EventService productService;

    @GetMapping
    public List<Event> getAll() {
        return productService.getAll();
    }

}
