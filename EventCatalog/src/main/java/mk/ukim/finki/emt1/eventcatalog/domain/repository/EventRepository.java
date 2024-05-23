package mk.ukim.finki.emt1.eventcatalog.domain.repository;


import mk.ukim.finki.emt1.eventcatalog.domain.models.EventId;
import mk.ukim.finki.emt1.eventcatalog.domain.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, EventId> {
}
