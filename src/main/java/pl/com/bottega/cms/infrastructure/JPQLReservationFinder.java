package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.ReservationDto;
import pl.com.bottega.cms.application.ReservationFinder;
import pl.com.bottega.cms.application.ReservationQuery;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Transactional
public class JPQLReservationFinder implements ReservationFinder {

    private EntityManager entityManager;

    private ReservationRepository reservationRepository;

    public JPQLReservationFinder(EntityManager entityManager, ReservationRepository reservationRepository) {
        this.entityManager = entityManager;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDto> search(ReservationQuery query) {
        //TODO
        List<Reservation> reservations = reservationRepository.findReservations(query);

        return null;
    }
}
