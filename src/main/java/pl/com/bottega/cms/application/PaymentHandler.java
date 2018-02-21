package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.PaymentCommand;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;
import pl.com.bottega.cms.domain.repositories.PaymentFacade;
import pl.com.bottega.cms.domain.repositories.Repository;

@Component
public class PaymentHandler implements Handler<PaymentCommand, PaymentStatus>{


    private PaymentFacade paymentFacade;

    private Repository<Reservation> repository;

    public PaymentHandler(PaymentFacade paymentFacade, Repository<Reservation> repository) {
        this.paymentFacade = paymentFacade;
        this.repository = repository;
    }


    @Override
    public PaymentStatus handle(PaymentCommand command) {
        Reservation reservation = repository.get(command.getReservationNumber());
        return paymentFacade.processPayment(command, reservation);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return PaymentCommand.class;
    }
}
