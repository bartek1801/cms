package pl.com.bottega.cms.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CinemaHallTest {

    private static final int ROWS = 10;
    private static final int SEATS = 15;
    private boolean[][] seats = new boolean[ROWS][SEATS];
    private CinemaHall sut = new CinemaHall(seats);

    private CreateReservationCommand command = new CreateReservationCommand();

    @MockBean
    private Customer customer;



    @Test
    public void shouldCheckSeatsAvailabilityWhereSeatsAreNotInTheSameRow(){
        //given
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
        command.setSeats(seats);
        command.setTickets(tickets);
        command.setCustomer(customer);

        try {
            sut.checkReservation(command);
        } catch (CommandInvalidException e) {
            assertThat(e.getValidationErrors().getErrors()).contains(entry("Seats", "The seats next to each other are available in a other row "));

        }

    }

    @Test
    public void shouldCheckSeatsAvailabilityWhereSeatsAreNotNextToEachOther(){
        //given
        Set<Ticket> tickets = new HashSet<>();
        Ticket ticket1 = new Ticket("regular", 2);
        Ticket ticket2 = new Ticket("student", 1);
        tickets.add(ticket1);
        tickets.add(ticket2);

        Set<Seat> seats = new HashSet<>();
        Seat seat1 = new Seat(1, 1);
        Seat seat2 = new Seat(1, 2);
        Seat seat3 = new Seat(1, 3);
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);

        command.setShowId(1L);
        command.setSeats(seats);
        command.setTickets(tickets);
        command.setCustomer(customer);

        try {
            sut.checkReservation(command);
        } catch (CommandInvalidException e) {
            assertThat(e.getValidationErrors().getErrors()).contains(entry( "Seats", "The seats next to each other are available in a other row " ));

        }

    }

    @Test
    public void shouldCheckSeatsAvailabilityWhenSeatsAreAvailable(){
        //given
        Set<Ticket> tickets = new HashSet<>();
        Ticket ticket1 = new Ticket("regular", 2);
        Ticket ticket2 = new Ticket("school", 1);
        tickets.add(ticket1);
        tickets.add(ticket2);

        Set<Seat> seats = new HashSet<>();
        Seat seat1 = new Seat(1, 1);
        Seat seat2 = new Seat(1, 2);
        seats.add(seat1);
        seats.add(seat2);

        command.setShowId(1L);
        command.setTickets(tickets);
        command.setSeats(seats);
        command.setCustomer(customer);

        try {
            sut.checkReservation(command);
        } catch (CommandInvalidException e) {
            assertThat(e.getValidationErrors().getErrors().isEmpty());
        }

    }

    @Test
    public void shouldCheckSeatsAvailabilityWhenSeatsAreNotAvailable() {
        // given
        Set<Ticket> tickets = new HashSet<>();
        Ticket ticket1 = new Ticket("regular", 1);
        Ticket ticket2 = new Ticket("school", 1);
        tickets.add(ticket1);
        tickets.add(ticket2);

        seats [1][1] = true;
        Set<Seat> seats = new HashSet<>();
        Seat seat1 = new Seat(2, 2);
        Seat seat2 = new Seat(2, 3);
        seats.add(seat1);
        seats.add(seat2);

        command.setShowId(1L);
        command.setTickets(tickets);
        command.setSeats(seats);
        command.setCustomer(customer);


        try {
            sut.checkReservation(command);
        } catch (CommandInvalidException e) {
            assertThat(e.getValidationErrors().getErrors()).contains(entry("Seat no " + seat1.getSeat() + " in row " + seat1.getRow() + " ", "is already reserved "));
        }
    }

}