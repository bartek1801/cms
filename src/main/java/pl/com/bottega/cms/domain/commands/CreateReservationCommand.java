package pl.com.bottega.cms.domain.commands;


import pl.com.bottega.cms.domain.Customer;
import pl.com.bottega.cms.domain.Seat;
import pl.com.bottega.cms.domain.Ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
        validateTickets(errors, tickets);
        validateUniqueTicketKinds(errors, tickets);
        validateTicketCountAndSeatsCount(errors, tickets, seats);
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

    private void validateTicketCountAndSeatsCount(ValidationErrors errors, Set<Ticket> tickets, Set<Seat> seats) {
        Integer ticketCount = tickets.stream().collect(Collectors.summingInt(Ticket::getCount));
        Integer seatsCount = seats.size();
        if (ticketCount != seatsCount)
            errors.add("Tickets or Seats ", "Count of tickets must be equal to count of seats");
    }

    private void validateTickets(ValidationErrors errors, Set<Ticket> tickets) {
        for(Ticket ticket : tickets) {
            if (ticket.getCount() == null || ticket.getCount() <= 0) {
                errors.add("ticket", "You must add at least one ticket");
            }
            if (ticket.getKind() == null) {
                errors.add("Ticket kind", "You must select ticket type");
            }
        }
    }

    private void validateUniqueTicketKinds(ValidationErrors errors, Set<Ticket> tickets) {
        List<String> ticketKinds = tickets.stream().map(Ticket::getKind).collect(Collectors.toList());
        boolean isTicketKindsAreUnique = new HashSet<String>(ticketKinds).size() != ticketKinds.size();
        if (isTicketKindsAreUnique)
            errors.add("Tickets", "Ticket kinds must be unique, they can't be repeated");
    }

    private void validateSeats(ValidationErrors errors) {
        if (seats == null || seats.isEmpty()) {
            errors.add("seats", "You must choose seats");
            throw new CommandInvalidException(errors);
        }
    }

    private void validateSeatsRows(ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.getRow() == null){
                errors.add("Seats: rows", "Can't be blank");
            }
             else if (seat.getRow() <= 0 || seat.getRow() > 10 ) {
                errors.add("Seats: rows", "row must be between 1 and 10");
            }
        }
    }

    private void validateSeatsNumbers(ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.getSeat() == null) {
                errors.add("Seats", "Can't be blank");
            }
            else if (seat.getSeat() <= 0 || seat.getSeat() > 15) {
                errors.add("Seats", "Seat must be between 1 and 15");
            }
        }
    }

    private void validateWithPattern(ValidationErrors errors, String email, String customerEmail, String emailFormat) {
        if (customerEmail != null && !customerEmail.matches(emailFormat)) {
            errors.add(email, "Invalid mail format");
        }
    }


}