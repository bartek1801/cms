package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.CinemaHall;
import pl.com.bottega.cms.domain.Seat;

import java.util.Set;

public class CinemaHallDto {

    private Set<Seat> free;
    private Set<Seat> occupied;

    public CinemaHallDto(CinemaHall cinemaHall) {
        //this.free =
        this.occupied = occupied;
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
