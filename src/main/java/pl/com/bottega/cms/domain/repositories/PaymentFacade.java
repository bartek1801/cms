package pl.com.bottega.cms.domain.repositories;

import com.stripe.exception.*;
import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.PaymentCommand;

public interface PaymentFacade {

    PaymentStatus processPayment(PaymentCommand cmd, Reservation reservation) ;
}
