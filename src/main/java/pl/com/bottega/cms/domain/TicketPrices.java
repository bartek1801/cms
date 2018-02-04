package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;

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

        public Receipt calculatePrice(CalculatePricesCommand command) {
           for(Ticket ticket : command.getTickets()){
               getTicketPrice(ticket.getKind());
            }
            return null;
        }

        private void getTicketPrice(String kind) {


        }


    }

