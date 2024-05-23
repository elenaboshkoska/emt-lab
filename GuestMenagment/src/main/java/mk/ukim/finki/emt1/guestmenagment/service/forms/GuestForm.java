package mk.ukim.finki.emt1.guestmenagment.service.forms;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.emt1.guestmenagment.domain.models.Registration;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
import mk.ukim.finki.emt1.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class GuestForm {
    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<RegistrationForm> items = new ArrayList<>();



}
