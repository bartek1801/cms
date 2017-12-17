package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.repositories.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Component
public class JPACinemaRepository implements CinemaRepository {

    private EntityManager entityManager;

    public JPACinemaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public boolean isCinemaExist(String name, String city) {
        return get(name, city).isPresent();
    }

    @Override
    public Optional<Cinema> get(String name, String city) {
        try {
            Cinema cinema = (Cinema) entityManager.createQuery("FROM Cinema c WHERE c.name = :name AND c.city = :city")
                    .setParameter("name", name)
                    .setParameter("city", city)
                    .getSingleResult();
            return Optional.of(cinema);
        } catch (NoResultException ex){
            return Optional.empty();
        }
    }


}
