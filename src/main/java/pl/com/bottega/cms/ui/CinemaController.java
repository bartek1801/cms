package pl.com.bottega.cms.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;

@RestController
public class CinemaController {

    private CommandGateway commandGateway;


    public CinemaController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/cinema")
    public void create(@RequestBody CreateCinemaCommand command) {
        commandGateway.execute(command);
    }

}

