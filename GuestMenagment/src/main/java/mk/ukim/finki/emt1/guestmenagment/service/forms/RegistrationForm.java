package mk.ukim.finki.emt1.guestmenagment.service.forms;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.emt1.guestmenagment.domain.ValueObject.Event;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Min;

@Data
public class RegistrationForm {
    @NotNull
    private Event event;

    @Min(1)
    private int quantity = 1;

}
