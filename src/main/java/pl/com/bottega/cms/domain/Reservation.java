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

    private Long showId;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "reservation")
    private Set<Ticket> tickets;

    @OneToMany
    private Set<Seat> seats;

    public Reservation() {
    }

    public Reservation(CreateReservationCommand cmd) {
        this.customer = cmd.getCustomer();
        this.tickets = cmd.getTickets();
        this.seats = cmd.getSeats();
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
