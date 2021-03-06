package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.Show;
import pl.com.bottega.cms.domain.repositories.GenericJPARepository;
import pl.com.bottega.cms.domain.repositories.ShowRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JPAShowRepository extends GenericJPARepository<Show> implements ShowRepository{

    @Override
    public List<Show> find(LocalDateTime date, Cinema cinema, Movie movie) {
        return entityManager.createQuery("SELECT s FROM Show s WHERE s.date = :date AND s.cinema = :cinema AND s.movie = :movie")
                .setParameter("date", date)
                .setParameter("cinema", cinema)
                .setParameter("movie", movie)
                .getResultList();
    }
}

