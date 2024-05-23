package mk.ukim.finki.emt1.guestmenagment.domain.repository;

import mk.ukim.finki.emt1.guestmenagment.domain.models.Guest;
import mk.ukim.finki.emt1.guestmenagment.domain.models.GuestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, GuestId> {
}

