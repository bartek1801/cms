package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

    @Embeddable
    public class TicketPrices {

        @ElementCollection
        private Map<String, BigDecimal> prices = new HashMap<>();

        @OneToOne()
        private Movie movie;

        public TicketPrices(){}

        public TicketPrices(Long id, Map<String, BigDecimal> prices ) {
            this.prices = prices;
        }


        public Map<String, BigDecimal> getPrices() {
            return prices;
        }

        public void setPrices(Map<String, BigDecimal> prices) {
            this.prices = prices;
        }
    }

