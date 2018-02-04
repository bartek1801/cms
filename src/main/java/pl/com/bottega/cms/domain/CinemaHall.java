package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;
import pl.com.bottega.cms.domain.commands.ValidationErrors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CinemaHall {

    private static final int ROWS = 10;
    private static final int SEATS = 15;


    private boolean[][] seats = new boolean[ROWS][SEATS];



    public CinemaHall(Set<Reservation> currentReservations) {
        for (Reservation reservation : currentReservations) {
            Set<Seat> reservedSeats = reservation.getSeats();
            for (Seat seat : reservedSeats) {
                seats[seat.getRow() - 1][seat.getSeat() - 1] = true;
            }
        }
    }

    public void checkReservation(CreateReservationCommand command) {
        checkSeatsAvailability(command.getSeats());
        countFreeSeats(seats);
        ckeckSeatNumbersOrder(command.getSeats());
    }

    private void countFreeSeats(boolean[][] seats) {
        //TODO
    }

    private void ckeckSeatNumbersOrder(Set<Seat> seatsFromReservation) {
        List<Integer> seatsNumbers = seatsFromReservation.stream().map(s -> s.getSeat()) .collect(Collectors.toList());
        Collections.sort(seatsNumbers);
        for (int i = seatsNumbers.get(0); i < seatsNumbers.size(); i++){
            if (seatsNumbers.get(i) + 1 != seatsNumbers.get(i + 1)){
                ValidationErrors errors = new ValidationErrors();
                errors.add("seats", "Seats must be next to each other");
                throw new CommandInvalidException(errors);
                // wyjątem rzuca gdy nie ma tylu wolnych miejsc, może wybrać miejsca
                // nie pokolei pod warunkiem że nie ma wolnych np 3 miejsc obok siebie
            }
        }
        //TODO sprawdzenie czy miejsca są obok siebie
    }

    private void checkSeatsAvailability(Set<Seat> commandSeats) {
        for (Seat seat : commandSeats) {
            Integer row = seat.getRow();
            Integer seatNo = seat.getSeat();
            if (seats[row - 1][seatNo - 1]) {
                ValidationErrors errors = new ValidationErrors();
                errors.add("seats", "Seat is already reserved ");
                throw new CommandInvalidException(errors);
            }
        }
    }


}
