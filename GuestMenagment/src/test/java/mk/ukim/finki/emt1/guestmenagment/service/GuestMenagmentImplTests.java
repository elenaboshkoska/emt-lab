package mk.ukim.finki.emt1.guestmenagment.service;

import mk.ukim.finki.emt1.guestmenagment.domain.ValueObject.Event;
import mk.ukim.finki.emt1.guestmenagment.domain.ValueObject.EventId;
import mk.ukim.finki.emt1.guestmenagment.domain.exceptions.IdDoesNotExists;
import mk.ukim.finki.emt1.guestmenagment.domain.models.Guest;
import mk.ukim.finki.emt1.guestmenagment.domain.models.GuestId;
import mk.ukim.finki.emt1.guestmenagment.service.forms.GuestForm;
import mk.ukim.finki.emt1.guestmenagment.service.forms.RegistrationForm;
import mk.ukim.finki.emt1.guestmenagment.xport.client.EventClient;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Money;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class GuestMenagmentImplTests {
    @Autowired
    private GuestService guestService;

    @Autowired
    private EventClient eventClient;



    private static Event newProduct(String name, Money price) {
        Event p = new Event(EventId.randomId(EventId.class), name, price);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        RegistrationForm oi1 = new RegistrationForm();
        oi1.setEvent(newProduct("Pizza",Money.valueOf(Currency.MKD,1500)));
        oi1.setQuantity(1);

        RegistrationForm oi2 = new RegistrationForm();
        oi2.setEvent(newProduct("Hot Dog",Money.valueOf(Currency.MKD,500)));
        oi2.setQuantity(2);

        GuestForm orderForm = new GuestForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        GuestId newOrderId = guestService.placeOrder(orderForm);
        Guest newOrder = guestService.findById(newOrderId).orElseThrow(IdDoesNotExists::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,2500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Event> productList = eventClient.findAll();
        Event p1 = productList.get(0);
        Event p2 = productList.get(1);

        RegistrationForm oi1 = new RegistrationForm();
        oi1.setEvent(p1);
        oi1.setQuantity(1);

        RegistrationForm oi2 = new RegistrationForm();
        oi2.setEvent(p2);
        oi2.setQuantity(2);

        GuestForm orderForm = new GuestForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        GuestId newOrderId = guestService.placeOrder(orderForm);
        Guest newOrder = guestService.findById(newOrderId).orElseThrow(IdDoesNotExists::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }

}
