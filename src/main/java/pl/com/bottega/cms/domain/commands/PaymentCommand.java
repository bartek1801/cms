package pl.com.bottega.cms.domain.commands;

import pl.com.bottega.cms.domain.CreditCardData;
import pl.com.bottega.cms.domain.PaymentType;

public class PaymentCommand implements Command {

   private Long reservationNumber;

   private PaymentType type;

   private CreditCardData creditCard;


    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public CreditCardData getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardData creditCard) {
        this.creditCard = creditCard;
    }
}
