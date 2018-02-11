package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.CommandGateway;
import pl.com.bottega.cms.application.ReservationNumberDto;
import pl.com.bottega.cms.application.ReservationQuery;
import pl.com.bottega.cms.domain.Receipt;
import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private CommandGateway gateway;

    public ReservationController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping
    public ReservationNumberDto create(@RequestBody CreateReservationCommand cmd){
        ReservationNumberDto reservationNumber = gateway.execute(cmd);
        return reservationNumber;
    }


    @PostMapping("/price_calculator")
    public Receipt calculatePrices(@RequestBody CalculatePricesCommand cmd){
        Receipt receipt = gateway.execute(cmd);
        return receipt;
    }

    @GetMapping
    public void search(ReservationQuery query){
        //TODO
    }

}
