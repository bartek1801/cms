package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "reservation")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "seat")
    private Set<Seat> seats;

    public Reservation() {
    }

    public Reservation(CreateReservationCommand cmd) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
