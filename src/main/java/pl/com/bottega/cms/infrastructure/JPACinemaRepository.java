package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.repositories.CinemaRepository;
import pl.com.bottega.cms.domain.repositories.GenericJPARepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Component
public class JPACinemaRepository extends GenericJPARepository<Cinema> implements CinemaRepository {



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

    @Override
    public Cinema get(Long cinemaId) {
        Cinema cinema = entityManager.find(Cinema.class, cinemaId);
        if (cinema == null)
            throw new NoSuchEntityException();
        return cinema;
    }


}
