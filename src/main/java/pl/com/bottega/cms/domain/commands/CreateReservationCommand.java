package pl.com.bottega.cms.domain.commands;


import pl.com.bottega.cms.domain.Customer;
import pl.com.bottega.cms.domain.Seat;
import pl.com.bottega.cms.domain.Ticket;


import java.util.Set;

public class CreateReservationCommand implements Command {

    private Long showId;

    private Customer customer;

    private Set<Ticket> tickets;

    private Set<Seat> seats;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
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

    public void validate(ValidationErrors errors){
        //TODO
    }
}
