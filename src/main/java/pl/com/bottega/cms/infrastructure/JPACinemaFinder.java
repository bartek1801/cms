package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.application.CinemaFinder;
import pl.com.bottega.cms.application.CinemaHallDto;
import pl.com.bottega.cms.domain.CinemaHall;
import pl.com.bottega.cms.domain.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JPACinemaFinder implements CinemaFinder {

    private EntityManager entityManager;

    public JPACinemaFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CinemaDto> getAll() {
        List<CinemaDto> results = entityManager.createQuery("SELECT NEW " +
                "pl.com.bottega.cms.application.CinemaDto(c.id, c.name, c.city) FROM Cinema c")
                .getResultList();
        return results;
    }

    @Override
    public CinemaHallDto getSeats(Long showId) {
        Query query = entityManager.createQuery(" FROM Reservation r WHERE r.showId = :showId");
        query.setParameter("showId", showId);
        Set<Reservation> result =  new HashSet<>();
        result.addAll(query.getResultList());
        //Set<Reservation> result = new JPAReservationRepository(entityManager).getReservations(showId);
        return new CinemaHallDto(new CinemaHall(result));

    }
}
