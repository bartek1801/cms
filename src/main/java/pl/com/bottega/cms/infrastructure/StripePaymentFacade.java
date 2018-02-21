package pl.com.bottega.cms.infrastructure;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.CreditCardData;
import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.PaymentCommand;
import pl.com.bottega.cms.domain.repositories.PaymentFacade;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripePaymentFacade implements PaymentFacade {

    public PaymentStatus processPayment(PaymentCommand cmd, Reservation reservation){
//        Stripe.apiKey = "pk_test_acdJmaNkZciIsxPYy0iIiIxz";
        Stripe.apiKey = "sk_test_eTWXlUHaZC3ecHYLf9RL5ljO";
        PaymentStatus paymentStatus = new PaymentStatus();
        Token token;
        try {
            token = createToken(cmd);
        } catch (CardException | APIException | AuthenticationException | APIConnectionException | InvalidRequestException e) {
            paymentStatus.setSuccess(false);
            paymentStatus.setErrorMessage(e.getMessage());
            return paymentStatus;
        }

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", reservation.getTotalCost().intValue()); //TODO zmianić kwotę na pełną
        chargeParams.put("currency", "usd");
        chargeParams.put("description", " Payment for reservation number: " + reservation.getId());
        chargeParams.put("capture", false);
        chargeParams.put("source", token);

        Charge charge;
        try {
            charge = Charge.create(chargeParams);
        } catch (AuthenticationException | InvalidRequestException | CardException | APIConnectionException | APIException e) {
            paymentStatus.setSuccess(false);
            paymentStatus.setErrorMessage(e.getMessage());
            return paymentStatus;
        }

        paymentStatus.setSuccess(charge.getCaptured());
        if (!charge.getCaptured())
            paymentStatus.setErrorMessage(charge.getFailureMessage());
        return paymentStatus;
    }


    private Token createToken(PaymentCommand cmd) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Stripe.apiKey = "sk_test_eTWXlUHaZC3ecHYLf9RL5ljO";
        Map<String, Object> tokenParams = new HashMap<>();

        CreditCardData creditCardData = cmd.getCreditCard();
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", creditCardData.getNumber());
        cardParams.put("exp_month", creditCardData.getExpirationMonth());
        cardParams.put("exp_year", creditCardData.getExpirationYear());
        cardParams.put("cvc", creditCardData.getCvc());

        tokenParams.put("card", cardParams);
        return Token.create(tokenParams);

    }

}
