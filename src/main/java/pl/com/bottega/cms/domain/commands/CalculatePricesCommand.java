package pl.com.bottega.cms.domain.commands;

import pl.com.bottega.cms.domain.Ticket;

import java.util.Set;

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
    }
}
