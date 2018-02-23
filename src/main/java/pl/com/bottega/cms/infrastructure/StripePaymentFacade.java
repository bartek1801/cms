package pl.com.bottega.cms.infrastructure;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.*;
import pl.com.bottega.cms.domain.commands.PaymentCommand;
import pl.com.bottega.cms.domain.repositories.PaymentFacade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class StripePaymentFacade implements PaymentFacade {

    public PaymentStatus processPayment(PaymentCommand cmd, Reservation reservation){
        Stripe.apiKey = "sk_test_eTWXlUHaZC3ecHYLf9RL5ljO";
        String token = "tok_visa";
//        String token = "tok_avsFail";
        PaymentStatus paymentStatus = new PaymentStatus();

        Map<String, Object> chargeParams = createChargeParams(reservation, token);
        Charge charge = new Charge();
        try {
            charge = Charge.create(chargeParams);
        } catch (AuthenticationException | InvalidRequestException | CardException | APIConnectionException | APIException e) {
            paymentStatus.setSuccess(false);
            paymentStatus.setMessage(e.getMessage());
            reservation.addTransaction(createTransaction(charge));
            return paymentStatus;
        }

        if (charge.getFailureMessage() != null) {
            paymentStatus.setMessage(charge.getFailureMessage());
            reservation.addTransaction(createTransaction(charge));
        }else {
            paymentStatus.setSuccess(true);
            paymentStatus.setMessage(charge.getOutcome().getSellerMessage());
            reservation.addTransaction(createTransaction(charge));
        }
        return paymentStatus;
    }

    private PaymentTransaction createTransaction(Charge charge) {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setSuccess(false);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionNumber(String.valueOf(charge.getCreated()));
        transaction.setType(charge.getSource().getObject());
        transaction.setDescription(charge.getDescription());
        transaction.setAmount(BigDecimal.valueOf(charge.getAmount()));
        return transaction;
    }

    private Map<String, Object> createChargeParams(Reservation reservation, String token) {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", reservation.getTotalCost().multiply(BigDecimal.valueOf(100)).intValue()); //kwota musi być pdana w centach i być całkowita
        chargeParams.put("currency", "usd");
        chargeParams.put("description", " Payment for reservation number: " + reservation.getId());
        chargeParams.put("capture", true);
        chargeParams.put("source", token);
        return chargeParams;
    }

}
