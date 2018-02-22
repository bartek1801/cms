package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.ReservationStatus;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.PaymentCommand;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;
import pl.com.bottega.cms.domain.repositories.PaymentFacade;
import pl.com.bottega.cms.domain.repositories.Repository;

@Component
@Transactional
public class PaymentHandler implements Handler<PaymentCommand, PaymentStatus>{


    private PaymentFacade paymentFacade;

    private Repository<Reservation> repository;

    public PaymentHandler(PaymentFacade paymentFacade, Repository<Reservation> repository) {
        this.paymentFacade = paymentFacade;
        this.repository = repository;
    }


    @Override
    public PaymentStatus handle(PaymentCommand command) {
        //TODO zrobić zapisywanie transakcji do bazy!!!
        Reservation reservation = repository.get(command.getReservationNumber());
        PaymentStatus paymentStatus = new PaymentStatus();
        if (!canPaidFor(reservation)) {
            return cancelPayment(paymentStatus, "Reservation id already paid");
        }

        paymentStatus = paymentFacade.processPayment(command, reservation);

        if (!paymentStatus.isSuccess())
            reservation.markAsPaymentFailed();

        reservation.markAsPaid();
        return paymentStatus;
    }

    private PaymentStatus cancelPayment(PaymentStatus paymentStatus, String reason) {
        paymentStatus.setSuccess(false);
        paymentStatus.setMessage(reason);
        return paymentStatus;
    }

    private boolean canPaidFor(Reservation reservation) {
        return reservation.getReservationStatus().compareTo(ReservationStatus.PENDING) == 0
                || reservation.getReservationStatus().compareTo(ReservationStatus.PAYMENT_FAILED) == 0 ;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return PaymentCommand.class;
    }
}
