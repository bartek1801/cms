package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class TicketPrices {

    public static final BigDecimal TICKET_DOES_NOT_EXIST = BigDecimal.valueOf(-1);

    @ElementCollection
    private Map<String, BigDecimal> prices = new HashMap<>();

    @OneToOne()
    private Movie movie;

    public TicketPrices() {
    }

    public TicketPrices(Long id, Map<String, BigDecimal> prices) {
        this.prices = prices;
    }


    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public Receipt calculatePrice(CalculatePricesCommand command) {
        Receipt receipt = new Receipt();
        for (Ticket ticket : command.getTickets()) {
            if (getTicketPrice(ticket.getKind()).equals(TICKET_DOES_NOT_EXIST))
                continue;
            else {
                receipt.addReceiptLine(ticket.getKind(), ticket.getCount(), getTicketPrice(ticket.getKind()));
            }
        }
        receipt.calculateTotalPrice();
        return receipt;
    }

    private BigDecimal getTicketPrice(String kind) {
        if (prices.containsKey(kind))
            return prices.get(kind);
        return TICKET_DOES_NOT_EXIST;
    }


}

