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

    @Id
    @GeneratedValue
    private Long id;

    private boolean [][] seats;

    public CinemaHall(Set<Reservation> currentReservations){
        //TODO - wypełnić tablicę seats i sprawdzić rezerwację
    }

    public void checkReservation(CreateReservationCommand cmd){

    }
}
