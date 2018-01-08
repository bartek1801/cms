package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.*;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.com.bottega.cms.domain.commands.CreateShowsCommand;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CommandGateway commandGateway;
    private CinemaFinder cinemaFinder;
    private MovieFinder movieFinder;


    public CinemaController(CommandGateway commandGateway, CinemaFinder cinemaFinder, MovieFinder movieFinder) {
        this.commandGateway = commandGateway;
        this.cinemaFinder = cinemaFinder;
        this.movieFinder = movieFinder;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand command) {
        commandGateway.execute(command);
    }


    @PutMapping("/{cinemaId}/shows")
    public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand command){
        command.setCinemaId(cinemaId);
        commandGateway.execute(command);
    }

    @GetMapping
    public List<CinemaDto> getCinemasList() {
        return cinemaFinder.getAll();
    }

    @GetMapping("/{cinemaId}/movies")
    public List<MovieDto> getShows(@PathVariable("cinemaId") Long cinemaId, @RequestParam String date){
        return movieFinder.getFromDay(cinemaId, LocalDate.parse(date));
    }

}

