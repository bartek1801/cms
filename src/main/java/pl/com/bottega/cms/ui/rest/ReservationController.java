package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping
    public Long create(@RequestBody CreateReservationCommand cmd){
        Long id = gateway.execute(cmd);
        return id; //TODO zmianić na ReservationDto
    }


}
