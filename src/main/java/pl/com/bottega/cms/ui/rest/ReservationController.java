package pl.com.bottega.cms.ui.rest;

import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.application.*;
import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.Receipt;
import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;
import pl.com.bottega.cms.domain.commands.GenerateTicketsCommand;
import pl.com.bottega.cms.domain.commands.PaymentCommand;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class ReservationController {

    private CommandGateway gateway;

    private ReservationFinder reservationFinder;

    private PaymentStatus paymentStatus;

    public ReservationController(CommandGateway gateway, ReservationFinder reservationFinder) {
        this.gateway = gateway;
        this.reservationFinder = reservationFinder;
    }

    @PutMapping("/reservations")
    public ReservationNumberDto create(@RequestBody CreateReservationCommand cmd) {
        ReservationNumberDto reservationNumber = gateway.execute(cmd);
        return reservationNumber;
    }


    @PostMapping("/price_calculator")
    public Receipt calculatePrices(@RequestBody CalculatePricesCommand cmd) {
        Receipt receipt = gateway.execute(cmd);
        return receipt;
    }

    @GetMapping("/reservations")
    public List<ReservationDto> search(ReservationQuery query) {
        return reservationFinder.search(query);
    }

    @GetMapping("reservations/{reservationNumber}/tickets")
    public void getTickets(@PathVariable Long reservationNumber, HttpServletResponse response) throws IOException {
        GenerateTicketsCommand cmd = new GenerateTicketsCommand();
        cmd.setReservationNumber(reservationNumber);
        byte[] pdfData = gateway.execute(cmd);
        String fileName = String.format("Reservation_%d.pdf", reservationNumber);
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentLength(pdfData.length);
        response.setContentLength(pdfData.length);
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();

    }

    @PutMapping("/reservations/{reservationNumber}/payment")
    public PaymentStatus pay(@PathVariable Long reservationNumber, @RequestBody PaymentCommand command){
        command.setReservationNumber(reservationNumber);
        return gateway.execute(command);
    }

}
