package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.ReservationDto;
import pl.com.bottega.cms.application.ReservationFinder;
import pl.com.bottega.cms.application.ReservationQuery;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Transactional
public class JPQLReservationFinder implements ReservationFinder {

    private EntityManager entityManager;

    public JPQLReservationFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ReservationDto> search(ReservationQuery query) {
        //TODO
        return null;
    }
}
