package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.Seat;

public class SeatDto {


    private Integer row;

    private Integer seat;

    public SeatDto(Seat seat) {
        this.row = seat.getRow();
        this.seat = seat.getSeat();
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
