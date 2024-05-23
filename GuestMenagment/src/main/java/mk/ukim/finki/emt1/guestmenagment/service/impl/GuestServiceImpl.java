package mk.ukim.finki.emt1.guestmenagment.service.impl;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt1.guestmenagment.domain.exceptions.IdDoesNotExists;
import mk.ukim.finki.emt1.guestmenagment.domain.models.Guest;
import mk.ukim.finki.emt1.guestmenagment.domain.models.GuestId;
import mk.ukim.finki.emt1.guestmenagment.domain.models.Registration;
import mk.ukim.finki.emt1.guestmenagment.domain.models.RegistrationId;
import mk.ukim.finki.emt1.guestmenagment.domain.repository.GuestRepository;
import mk.ukim.finki.emt1.guestmenagment.service.GuestService;
import mk.ukim.finki.emt1.guestmenagment.service.forms.GuestForm;
import mk.ukim.finki.emt1.guestmenagment.service.forms.RegistrationForm;
import mk.ukim.finki.emt1.sharedkernel.domain.events.orders.GuestCreated;
import mk.ukim.finki.emt1.sharedkernel.infra.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class GuestServiceImpl implements GuestService {
    private final GuestRepository orderRepository;
    /*private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;*/

    @Override
    public GuestId placeOrder(GuestForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
     /*  var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }

        newOrder.getSignIn().forEach(item->domainEventPublisher.publish(new GuestCreated(item.getEventId().getId(),item.getQuantity())));*/
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }

    @Override
    public List<Guest> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Guest> findById(GuestId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(GuestId orderId, RegistrationForm orderItemForm) throws IdDoesNotExists {
        Guest order = orderRepository.findById(orderId).orElseThrow(IdDoesNotExists::new);
        order.addGuest(orderItemForm.getEvent(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
/*
        domainEventPublisher.publish(new GuestCreated(orderItemForm.getEvent().getId().getId(),orderItemForm.getQuantity()));
*/
    }

    @Override
    public void deleteItem(GuestId orderId, RegistrationId orderItemId) throws IdDoesNotExists {
        Guest order = orderRepository.findById(orderId).orElseThrow(IdDoesNotExists::new);
        order.removeGuest(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Guest toDomainObject(GuestForm orderForm) {
        var order = new Guest(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addGuest(item.getEvent(),item.getQuantity()));
        return order;
    }


}
