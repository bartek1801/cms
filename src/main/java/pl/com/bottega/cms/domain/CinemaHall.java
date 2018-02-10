package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;
import pl.com.bottega.cms.domain.commands.ValidationErrors;

import java.util.HashSet;
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

    public CinemaHall(boolean[][] seats) {
        this.seats = seats;
    }

    public void checkReservation(CreateReservationCommand command) {
        ValidationErrors errors = new ValidationErrors();

        checkSeatsAvailability(errors, command.getSeats());
        seatsAreInTheSameRow(errors, command.getSeats());
        seatAreNextToEachOther(errors, command.getSeats());

        if (errors.any())
            throw new CommandInvalidException(errors);

    }

    private void seatsAreInTheSameRow(ValidationErrors errors, Set<Seat> seats) {
        List<Integer> tmpList = seats.stream().map(s -> s.getRow()).collect(Collectors.toList());
        boolean allRowsEqual = new HashSet<Integer>(tmpList).size() <= 1;
        if (!allRowsEqual) {
            checkAbilityToReserveSeatsToAnotherPlace(errors, seats);
        }


    }

    private void seatAreNextToEachOther(ValidationErrors errors, Set<Seat> seats) {
        List<Integer> seatsNumbers = seats.stream().map(s -> s.getSeat()).sorted().collect(Collectors.toList());
        for (int i = seatsNumbers.get(0); i < seatsNumbers.size() - 1; i++) {
            if (seatsNumbers.get(i) + 1 != seatsNumbers.get(i + 1)) {
                checkAbilityToReserveSeatsToAnotherPlace(errors, seats);
            }
        }
    }

    private void checkAbilityToReserveSeatsToAnotherPlace(ValidationErrors errors, Set<Seat> seats) {
        if (seatsAreAvailebleInOtherPlace(seats.size())) {
            errors.add("Seats", "The seats next to each other are available in a other row ");
        }
    }

    private boolean seatsAreAvailebleInOtherPlace(int seatsCount) {
        for (int i = 0; i < seats.length; i++) {
            int tmpCount = 0;
            for (int k = 0; k < seats[i].length; k++) {
                if (seats[i][k])
                    tmpCount = 0;
                else
                    tmpCount += 1;
            }
            if (tmpCount >= seatsCount)
                return true;
        }
        return false;
    }


    private void checkSeatsAvailability(ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            Integer row = seat.getRow();
            Integer seatNo = seat.getSeat();
            if (this.seats[row - 1][seatNo - 1]) {
                errors.add("Seat no " + seatNo + " in row " + row + " ", "is already reserved ");
            }
        }

    }


}
