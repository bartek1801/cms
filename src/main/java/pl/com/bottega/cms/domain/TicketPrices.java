package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

    @Entity
    @Table(name = "ticket_prices")
    public class TicketPrices {

        @Id
        private Long id;

        @ElementCollection
        @CollectionTable(name = "price", joinColumns=@JoinColumn(name="ticket_prices_id"))
        @Column(name = "prices")
        private Map<String, BigDecimal> prices = new HashMap<>();

        @OneToOne()
        private Movie movie;

        public TicketPrices(){}

        public TicketPrices(Long id, Map<String, BigDecimal> prices ) {
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

