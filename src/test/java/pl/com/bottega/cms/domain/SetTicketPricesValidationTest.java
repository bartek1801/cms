package pl.com.bottega.cms.domain;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;
import pl.com.bottega.cms.domain.commands.ValidationErrors;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SetTicketPricesValidationTest {

    private ValidationErrors errors;
    private SetTicketPricesCommand command;
    private final  HashMap<String, BigDecimal> prices = new HashMap<>();

    @Before
    public void cleanUp(){
        errors = new ValidationErrors();
        command = new SetTicketPricesCommand();
    }

    @Test
    public void shouldReturnErrorWhenPricesValuesIsNegative(){
        prices.put("regular", BigDecimal.valueOf(-20));
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(10));
        prices.put("children", BigDecimal.valueOf(5));
        command.setPrices(prices);
        command.setMovieId(1L);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
        assertEquals(Arrays.asList("ticketRequiredPrices"), errors.getErrors().keySet().stream().collect(Collectors.toList()));
    }

    @Test
    public void shouldReturnErrorWhenRegularPriceIsNull(){
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(10));
        prices.put("children", BigDecimal.valueOf(5));
        command.setPrices(prices);
        command.setMovieId(1L);
        command.validate(errors);

        assertEquals(1, errors.getErrors().size());
        assertEquals(Arrays.asList("regular"), errors.getErrors().keySet().stream().collect(Collectors.toList()));
    }

}
