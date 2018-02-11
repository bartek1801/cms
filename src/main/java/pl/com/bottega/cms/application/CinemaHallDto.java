package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.CinemaHall;
import pl.com.bottega.cms.domain.Seat;

import java.util.HashSet;
import java.util.Set;

public class CinemaHallDto {

    private Set<Seat> free = new HashSet<>();
    private Set<Seat> occupied = new HashSet<>();

    public CinemaHallDto(CinemaHall cinemaHall) {
        boolean[][] reservation = cinemaHall.getSeats();
            for (int r = 1; r < cinemaHall.getROWS(); r++){
                for (int s = 1; s < cinemaHall.getSEATS(); s ++){
                    if (reservation[r][s] == true)
                        occupied.add(new Seat(r+ 1, s +1));
                    else
                        free.add(new Seat(r +1,s +1));
                }
            }
    }

    public Set<Seat> getFree() {
        return free;
    }

    public void setFree(Set<Seat> free) {
        this.free = free;
    }

    public Set<Seat> getOccupied() {
        return occupied;
    }

    public void setOccupied(Set<Seat> occupied) {
        this.occupied = occupied;
    }
}
