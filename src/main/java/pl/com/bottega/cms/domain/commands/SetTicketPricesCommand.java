package pl.com.bottega.cms.domain.commands;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void validate(ValidationErrors errors) {
        validatePresence(errors, "movieId", movieId);
        validatePresence(errors, "prices", prices);
        validateObligatoryFields(prices, errors, "student");
        validateObligatoryFields(prices, errors, "regular");
        validateObligatoryValues(prices, errors);
        validateNegativeValues(prices, errors);
    }

    private void validateNegativeValues(Map<String, BigDecimal> prices, ValidationErrors errors) {
        for(BigDecimal price: prices.values()){
            if(price.compareTo(BigDecimal.ZERO) < 0 )
                errors.add("ticketRequiredPrices", "price must be greater than zero");
        }
    }

    private void validateObligatoryValues(Map<String, BigDecimal> prices, ValidationErrors errors) {
        if(prices.values().contains(null) || prices.values().isEmpty())
            errors.add("ticketRequiredPrices", "ticket prices are required");
    }

    public void validateObligatoryFields(Map<String, BigDecimal> prices, ValidationErrors errors, String fieldName) {
        if (prices.keySet().stream().filter(key -> key.toLowerCase().equals(fieldName)).collect(Collectors.toList()).isEmpty()) {
            errors.add(fieldName, "Field with name '" + fieldName + "' is required.");
        }
    }


}
