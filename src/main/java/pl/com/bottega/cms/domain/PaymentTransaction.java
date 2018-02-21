package pl.com.bottega.cms.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
public class PaymentTransaction {

    @Id
    @GeneratedValue
    private Long id;

    private boolean success;

   // private PaymentType type;

    //private PaymentSystemDetails details;

    private LocalDateTime dateTime;

    private String transactionalNumber;

    public PaymentTransaction() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTransactionalNumber() {
        return transactionalNumber;
    }

    public void setTransactionalNumber(String transactionalNumber) {
        this.transactionalNumber = transactionalNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
