package pl.com.bottega.cms.domain.commands;


import pl.com.bottega.cms.domain.Customer;
import pl.com.bottega.cms.domain.Seat;
import pl.com.bottega.cms.domain.Ticket;

import java.util.Set;


public class CreateReservationCommand implements Command {

    private final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private Long showId;
    private Set<Ticket> tickets;
    private Set<Seat> seats;
    private Customer customer;

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

    public void validate(ValidationErrors errors) {
        validatePresence(errors, "showId", showId);
        validateTickets(errors);
        validateSeats(errors);
        validateSeatsNumbers(errors, seats);
        validateSeatsRows(errors, seats);
        validatePresence(errors, "customer", customer);
        if (customer != null) {
            validatePresence(errors, "firstName", customer.getFirstName());
            validatePresence(errors, "lastName", customer.getLastName());
            validatePresence(errors, "email", customer.getEmail());
            validateWithPattern(errors, "email_format", customer.getEmail(), EMAIL_REGEX);
            validatePresence(errors, "phone", customer.getPhone());
        }
    }

    public void validateTickets(ValidationErrors errors) {
        if (tickets == null || tickets.isEmpty()) {
            errors.add("ticket", "You must add at least one ticket");
        }
    }

    public void validateSeats(ValidationErrors errors) {
        if (seats == null || seats.isEmpty()) {
            errors.add("Seats", "You must choose seats");
            throw new CommandInvalidException(errors);
        }
    }

    private void validateSeatsRows(ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.getRow() <= 0 || seat.getRow() > 10) {
                errors.add("seats", "row must be between 1 and 10");
            }
        }
    }

    private void validateSeatsNumbers(ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.getSeat() <= 0 || seat.getSeat() > 15) {
                errors.add("seats", "seat must be between 1 and 15");
            }
        }
    }

    private void validateWithPattern(ValidationErrors errors, String email, String customerEmail, String emailFormat) {
        if (customerEmail != null && !customerEmail.matches(emailFormat)) {
            errors.add(email, "invalid mail format");
        }
    }
}