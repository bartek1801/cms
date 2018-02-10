package pl.com.bottega.cms.domain;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;
import pl.com.bottega.cms.domain.commands.ValidationErrors;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TicketPricesTest {

   private CalculatePricesCommand command;
   private final TicketPrices prices = new TicketPrices();
   private final Set<Ticket> tickets = new HashSet<>();
   private ValidationErrors errors;

    @Before
    public void cleanUp(){
        errors = new ValidationErrors();
        command = new CalculatePricesCommand();
    }

    @Test
    public void shouldCalculateTotalPrice() {
        tickets.add(new Ticket("regular", 2));
        tickets.add(new Ticket("student", 1));
        command.setShowId(1L);
        command.setTickets(tickets);
        prices.setPrices(createMap());

        assertEquals(BigDecimal.valueOf(50), prices.calculatePrice(command).getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceWhenCountIsNegative() {
        tickets.add(new Ticket("regular", -2));
        tickets.add(new Ticket("student", 1));
        command.setShowId(1L);
        command.setTickets(tickets);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
        assertEquals(Arrays.asList("Count"), errors.getErrors().keySet().stream().collect(Collectors.toList()));
    }

    @Test
    public void shouldCalculatePriceWhenTicketKindIsEmpty() {
        tickets.add(new Ticket("", 2));
        tickets.add(new Ticket("student", 1));
        command.setShowId(1L);
        command.setTickets(tickets);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
    }

    @Test
    public void shouldCalculatePriceWhenTicketKindIsNull() {
        tickets.add(new Ticket(null, 2));
        tickets.add(new Ticket("student", 1));
        command.setShowId(1L);
        command.setTickets(tickets);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
    }

    @Test
    public void shouldCalculatePriceWhenTicketCountIsNull() {
        tickets.add(new Ticket("regular", 2));
        tickets.add(new Ticket("student", null));
        command.setShowId(1L);
        command.setTickets(tickets);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
    }

    @Test
    public void shouldCalculatePriceWhenKindIsNotEqualWithSetTicketPriceKey(){
        tickets.add(new Ticket("student", 2));
        tickets.add(new Ticket("student", 1));
        command.setShowId(1L);
        command.setTickets(tickets);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
    }

    @Test
    public void shouldShowIdCanBeNegative(){
        tickets.add(new Ticket("student", 2));
        tickets.add(new Ticket("regular", 1));
        command.setShowId(-1L);
        command.setTickets(tickets);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
    }


    private HashMap<String, BigDecimal> createMap() {
        HashMap<String, BigDecimal> pricesMap = new HashMap<>();
        pricesMap.put("regular", BigDecimal.valueOf(20));
        pricesMap.put("student", BigDecimal.valueOf(10));
        return pricesMap;
    }

}


