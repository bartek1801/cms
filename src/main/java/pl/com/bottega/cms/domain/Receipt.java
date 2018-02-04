package pl.com.bottega.cms.domain;

import java.math.BigDecimal;
import java.util.Set;

public class Receipt {

    public Receipt(Set<ReceiptLine> tickets) {
        this.tickets = tickets;
    }

    private BigDecimal totalPrice;

    private Set<ReceiptLine> tickets;



    private class ReceiptLine {

        public ReceiptLine(String kind, Integer count, BigDecimal unitPrice, BigDecimal totalPrice) {
            this.kind = kind;
            this.count = count;
            this.unitPrice = unitPrice;
            this.totalPrice = totalPrice;
        }

        private String kind;

        private Integer count;

        private BigDecimal unitPrice;

        private BigDecimal totalPrice;


    }
}
