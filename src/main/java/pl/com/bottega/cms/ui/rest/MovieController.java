package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private CommandGateway gateway;

    public MovieController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping
    public void addMovie(@RequestBody CreateMovieCommand cmd) {
        gateway.execute(cmd);
    }

    @PutMapping("/{id}/prices")
    public void setTicketPrices(@PathVariable Long id, @RequestBody Map<String, BigDecimal> prices) {
        SetTicketPricesCommand command = new SetTicketPricesCommand();
        command.setMovieId(id);
        command.setPrices(prices);
        gateway.execute(command);
    }
}
