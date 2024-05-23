package mk.ukim.finki.emt1.eventcatalog.services.forms;

import lombok.Data;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Money;

@Data
public class EventForm {
    private String productName;
    private Money price;
    private int sales;

}