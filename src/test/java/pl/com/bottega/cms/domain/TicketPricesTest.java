package pl.com.bottega.cms.domain;

import org.junit.Test;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TicketPricesTest {

    private final Movie movie = new Movie();

    @Test
    public void shouldSetTicketPrices() {
        HashMap<String, BigDecimal> prices = new HashMap<>();
        prices.put("regular", BigDecimal.valueOf(20));
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(10));
        prices.put("children", BigDecimal.valueOf(5));
        SetTicketPricesCommand cmd = new SetTicketPricesCommand();
        cmd.setMovieId(1L);
        cmd.setPrices(prices);
        movie.setPrices(cmd);

        assertEquals(prices, movie.getTicketPricess().getPrices());
    }

    @Test
    public void shouldOverridePreviouslyPrices(){
        HashMap<String, BigDecimal> prices = new HashMap<>();
        prices.put("regular", BigDecimal.valueOf(20));
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(10));
        prices.put("children", BigDecimal.valueOf(5));
        SetTicketPricesCommand cmd = new SetTicketPricesCommand();
        cmd.setMovieId(1L);
        cmd.setPrices(prices);
        movie.setPrices(cmd);
        HashMap<String, BigDecimal> prices2 = new HashMap<>();
        prices.put("regular", BigDecimal.valueOf(50));
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(30));
        prices.put("children", BigDecimal.valueOf(5));
        cmd.setMovieId(1L);
        cmd.setPrices(prices2);
        movie.setPrices(cmd);
        assertEquals(prices2, movie.getTicketPricess().getPrices());
    }

    @Test
    public void shouldRegularPricesIsRequired(){
        HashMap<String, BigDecimal> prices = new HashMap<>();
        prices.put("regular", null);
        prices.put("student", BigDecimal.valueOf(10));
        prices.put("school", BigDecimal.valueOf(10));
        prices.put("children", BigDecimal.valueOf(5));
        SetTicketPricesCommand cmd = new SetTicketPricesCommand();

    }
}
