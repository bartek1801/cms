package pl.com.bottega.cms.domain.commands;

import pl.com.bottega.cms.domain.CreditCardData;
import pl.com.bottega.cms.domain.PaymentType;

public class PaymentCommand implements Command {

   private Long reservationNumber;

   private PaymentType paymentType;

   private CreditCardData creditcard;

}
