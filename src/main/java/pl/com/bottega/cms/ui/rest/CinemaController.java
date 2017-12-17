package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.application.CinemaFinder;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.com.bottega.cms.domain.commands.CreateShowsCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CommandGateway commandGateway;
    private CinemaFinder cinemaFinder;


    public CinemaController(CommandGateway commandGateway, CinemaFinder cinemaFinder) {
        this.commandGateway = commandGateway;
        this.cinemaFinder = cinemaFinder;
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

}

