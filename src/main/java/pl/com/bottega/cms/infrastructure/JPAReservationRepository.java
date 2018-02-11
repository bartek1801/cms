package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.application.ReservationQuery;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.repositories.GenericJPARepository;
import pl.com.bottega.cms.domain.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JPAReservationRepository extends GenericJPARepository<Reservation> implements ReservationRepository{

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
        Query query = entityManager.createQuery(" FROM Reservation r WHERE r.showId = :showId");
        query.setParameter("showId", showId);
        Set<Reservation> result =  new HashSet<>();
        result.addAll(query.getResultList());
        return  result;
    }


}