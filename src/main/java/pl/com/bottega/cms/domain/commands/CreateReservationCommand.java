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

    private final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

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
        validatePresence(errors, "tickets", tickets);
        validatePresence(errors, "seats", seats);
        validatePresence(errors, "customer", customer);
        validatePresence(errors, "firsName", customer.getFirstName());
        validatePresence(errors, "lastName", customer.getLastName());
        validateWithPattern(errors, "email", customer.getEmail(), EMAIL_REGEX);
        validatePresence(errors, "phone", customer.getPhone());
    }

    public void validateTickets(ValidationErrors errors) {
        if (tickets.isEmpty()) {
            errors.add("ticket", "you must add at least one ticket");
        }
    }

    private void validateSeatsRows(ValidationErrors errors, Set<Seat> seats) {

        for (Seat seat : seats) {
            if (seat.getRow() <= 0 && seat.getRow() > 10) {
                errors.add("seats", "row must be between 1 and 10");
            }
        }
    }

    private void validateSeatsNumbers(ValidationErrors errors, Set<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.getSeat() <= 0 && seat.getSeat() >= 15) {
                errors.add("seats", "seat number from 1 to 15");
            }
        }
    }

    private void validateCustomerDetails(ValidationErrors errors, Customer customers) {
        validatePresence(errors, "customer: first name", customer.getFirstName());
        validatePresence(errors, "customer: last name", customer.getLastName());
        validatePresence(errors, "customer: email address", customer.getEmail());
        validatePresence(errors, "customer: phone number", customer.getPhone());
//            if (customer.getFirstName().isEmpty() && customer.getLastName().isEmpty() && customer.getEmail().isEmpty() && customer.getPhone().isEmpty()) {
//                errors.add("customers", "all fields must be completed");
    }

    private void validateWithPattern(ValidationErrors errors, String email, String customerEmail, String emailFormat) {
        if (email != null && email.matches(emailFormat)) {
            errors.add(email, "invalid format");
        }
    }
}
