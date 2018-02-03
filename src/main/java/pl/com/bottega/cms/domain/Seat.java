package pl.com.bottega.cms.domain;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    private Integer row;

    private Integer seatNumber;

    @ManyToOne
    private Reservation reservation;

    public Seat() {
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
}
