package pl.com.bottega.cms.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.application.CinemaHallDto;
import pl.com.bottega.cms.application.CreateReservationHandler;
import pl.com.bottega.cms.domain.CinemaHall;
import pl.com.bottega.cms.domain.Customer;
import pl.com.bottega.cms.domain.Seat;
import pl.com.bottega.cms.domain.Ticket;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;
import pl.com.bottega.cms.infrastructure.JPACinemaFinder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FindAvailableSeatsTest extends AcceptanceTest {

    @Autowired
    private JPACinemaFinder cinemaFinder;
    @Autowired
    private CreateReservationHandler handler;

    private CreateReservationCommand command;

    private CinemaHall cinemaHall;

    private Customer customer;

    @Test
    public void shouldFindFreeAndOccupiedSeats() {

        Set<Ticket> tickets = new HashSet<>();
        Ticket ticket1 = new Ticket("regular", 2);
        Ticket ticket2 = new Ticket("student", 1);
        tickets.add(ticket1);
        tickets.add(ticket2);

        Set<Seat> seats = new HashSet<>();
        Seat seat1 = new Seat(1, 1);
        Seat seat2 = new Seat(1, 2);
        Seat seat3 = new Seat(2, 3);
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);

        command.setShowId(1L);
        command.setTickets(tickets);
        command.setSeats(seats);
        //command.setCustomer(customer);
        handler.handle(command);

        CinemaHallDto cinemaHallDto = cinemaFinder.getSeats(1L);
        assertEquals(command.getSeats(), (cinemaHallDto.getOccupied()));

    }

}
