package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private CommandGateway gateway;

    public ReservationController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    public void create(CreateReservationCommand cmd){
        gateway.execute(cmd);

    }


}
