package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.MovieFinder;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private CommandGateway gateway;
    private MovieFinder movieFinder;

    public MovieController(CommandGateway gateway, MovieFinder movieFinder) {
        this.gateway = gateway;
        this.movieFinder = movieFinder;
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

    @GetMapping("/{id}")
    public MovieDto get(@PathVariable Long id) {
        return movieFinder.get(id);
    }
}
