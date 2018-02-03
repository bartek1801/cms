package pl.com.bottega.cms.domain.commands;

import java.math.BigDecimal;
import java.util.Map;

public class SetTicketPricesCommand implements Command {

    Long movieId;

    Map<String, BigDecimal> prices;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }
}
