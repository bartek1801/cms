package pl.com.bottega.cms.domain;

public class PaymentType {

    private String type;

    public PaymentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
