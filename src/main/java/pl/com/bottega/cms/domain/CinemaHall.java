package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "cinema_halls")
public class CinemaHall {

    public static final int ROWS = 10;
    public static final int SEATS = 15;

    @Id
    @GeneratedValue
    private Long id;

    private boolean [][] seats =  new boolean[ROWS][SEATS];

    public CinemaHall(Set<Reservation> currentReservations){
        //TODO - wypełnić tablicę seats
        for (Reservation reservation : currentReservations){
            Set<Seat> reservedSeats = reservation.getSeats();
            for (Seat seat : reservedSeats) {
                seats[seat.getRow()][seat.getSeatNumber()] = true;
            }
        }
    }

    public void checkReservation(CreateReservationCommand cmd){

        //throw CommanValodationException
    }


}
