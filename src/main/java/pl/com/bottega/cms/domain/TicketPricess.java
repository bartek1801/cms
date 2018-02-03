package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "ticket_pricess")
public class TicketPricess {

    @Id
    private Long id;

    @ElementCollection
    private Map<String, BigDecimal> prices = new HashMap<String, BigDecimal>();

    @OneToOne()
    private Movie movie;

    public TicketPricess(){}

    public TicketPricess(Long id, Map<String, BigDecimal> prices ) {
        this.id = id;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }
}
