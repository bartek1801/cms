package pl.com.bottega.cms.domain;

import java.time.LocalDateTime;
import java.util.Map;

public class PaymentTransaction {

    private boolean success;

    private PaymentType type;

    //private PaymentSystemDetails details;

    private LocalDateTime dateTime;

    String transactionalNumber;

}
