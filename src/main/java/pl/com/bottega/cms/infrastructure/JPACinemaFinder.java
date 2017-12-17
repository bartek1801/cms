package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.application.CinemaFinder;

import javax.persistence.EntityManager;
import java.util.List;

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
}
