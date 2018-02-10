package pl.com.bottega.cms.domain.commands;

import pl.com.bottega.cms.domain.Ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CalculatePricesCommand implements Command {

    private Long showId;

    private Set<Ticket> tickets;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void validate(ValidationErrors errors) {

        validatePresence(errors, "showId", showId);
        validatePresence(errors, "tickets", tickets);
        validateNumber(errors, showId);

        validateTicket(errors, tickets);
        validateTicketKindsUniqueness(errors,tickets);
        validateTicketFields(errors, tickets);
        validateCount(errors, tickets);
    }

    private void validateTicket(ValidationErrors errors, Set<Ticket> tickets) {
        if (!(tickets == null))
            if (tickets.size() == 0 || tickets.isEmpty())
                errors.add("ticket", "tickets cannot be empty");
    }

    private void validateTicketFields(ValidationErrors errors, Set<Ticket> tickets) {
        if ( !(tickets == null)) {
            for (Ticket ticket : tickets) {
                if (ticket.getCount() == null)
                    errors.add("ticketCount", "ticket count is requiered");
                if (ticket.getKind() == null ||ticket.getKind().isEmpty())
                    errors.add("ticketKind", "ticket kind is requiered");
            }
        }
    }

    private void validateTicketKindsUniqueness(ValidationErrors errors, Set<Ticket> tickets) {
        if (!(tickets == null)) {
            List<String> ticketKinds = tickets.stream().map(Ticket::getKind).collect(Collectors.toList());
            boolean isTicketKindsAreUnique = new HashSet<String>(ticketKinds).size() != ticketKinds.size();
            if (isTicketKindsAreUnique)
                errors.add("Tickets", "Ticket kinds must be unique, they can't be repeated");
        }
    }

    private void validateNumber(ValidationErrors errors, Long showId) {
        if (!(showId == null)) {
            if (showId <= 0)
                errors.add("showId", "ShowId must be number greater than zero");
        }
    }

    private void validateCount(ValidationErrors errors, Set<Ticket> tickets) {
        if (!(tickets == null)) {
           for(Ticket ticket: tickets) {
               if(ticket.getCount() != null)
              if(ticket.getCount() < 0)
                  errors.add("Count", "Count must be greater than zero");
        }
    }
    }
}
