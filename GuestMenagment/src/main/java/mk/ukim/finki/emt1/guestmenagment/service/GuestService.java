package mk.ukim.finki.emt1.guestmenagment.service;

import mk.ukim.finki.emt1.guestmenagment.domain.exceptions.IdDoesNotExists;
import mk.ukim.finki.emt1.guestmenagment.domain.models.Guest;
import mk.ukim.finki.emt1.guestmenagment.domain.models.GuestId;
import mk.ukim.finki.emt1.guestmenagment.domain.models.RegistrationId;
import mk.ukim.finki.emt1.guestmenagment.service.forms.GuestForm;
import mk.ukim.finki.emt1.guestmenagment.service.forms.RegistrationForm;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    GuestId placeOrder(GuestForm orderForm);

    List<Guest> findAll();

    Optional<Guest> findById(GuestId id);

    void addItem(GuestId orderId, RegistrationForm orderItemForm) throws IdDoesNotExists;

    void deleteItem(GuestId orderId, RegistrationId orderItemId) throws IdDoesNotExists;
}
