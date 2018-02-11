package pl.com.bottega.cms.infrastructure;

import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.PaymentCommand;
import pl.com.bottega.cms.domain.repositories.PaymentFacade;

public class StripePaymentFacade implements PaymentFacade {

    public PaymentStatus processPayment(PaymentCommand cmd, Reservation reservation){
        return null;
    }

}
