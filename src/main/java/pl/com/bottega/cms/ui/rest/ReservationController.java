package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.*;
import pl.com.bottega.cms.domain.Receipt;
import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

import java.util.List;

@RestController
@RequestMapping
public class ReservationController {

    private CommandGateway gateway;

    private ReservationFinder reservationFinder;

    public ReservationController(CommandGateway gateway, ReservationFinder reservationFinder) {
        this.gateway = gateway;
        this.reservationFinder = reservationFinder;
    }

    @PutMapping("/reservations")
    public ReservationNumberDto create(@RequestBody CreateReservationCommand cmd){
        ReservationNumberDto reservationNumber = gateway.execute(cmd);
        return reservationNumber;
    }


    @PostMapping("/price_calculator")
    public Receipt calculatePrices(@RequestBody CalculatePricesCommand cmd){
        Receipt receipt = gateway.execute(cmd);
        return receipt;
    }

    @GetMapping("/reservations")
    public List<ReservationDto> search(ReservationQuery query){
        return reservationFinder.search(query);
    }

}
