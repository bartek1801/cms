package pl.com.bottega.cms.domain;

import javax.persistence.*;

@Embeddable
public class Seat {


    private Integer row;

    private Integer seat;

    public Seat() {

    }

    public Seat(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }

    public Seat(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }


    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
