package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.Ticket;

public class TicketDto {


    private String kind;

    private Integer count;

    public TicketDto(Ticket ticket) {
        this.kind = ticket.getKind();
        this.count = ticket.getCount();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
