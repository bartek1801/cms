package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;

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
}
