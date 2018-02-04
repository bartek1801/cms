package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import java.util.Set;

@Component
public class JPAReservationRepository implements ReservationRepository{

    private EntityManager entityManager;

    public JPAReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Set<Reservation> getReservations(Long showId) {

        return null;
    }
}
