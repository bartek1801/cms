package pl.com.bottega.cms.domain.repositories;

import pl.com.bottega.cms.domain.Reservation;

import java.util.Set;

public interface ReservationRepository {

    void save(Reservation reservation);

   Set<Reservation> getReservations(Long showId);
}
