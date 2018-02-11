package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.ReservationStatus;

public class ReservationQuery {

    private String query;

    private ReservationStatus status;

    public ReservationQuery(String query, ReservationStatus status) {
        this.query = query;
        this.status = status;
    }

    public ReservationQuery() {}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
