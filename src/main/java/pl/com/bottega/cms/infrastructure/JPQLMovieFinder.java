package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.MovieFinder;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
public class JPQLMovieFinder implements MovieFinder {

    private EntityManager entityManager;

    public JPQLMovieFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<MovieDto> getFromDay(Long cinemaId, LocalDate date) {
        //TODO poprawić JPQL-a !!!!!!!!!!!!
        Cinema cinema = entityManager.find(Cinema.class, cinemaId);
        Query query = entityManager.createQuery("SELECT " +
                "NEW  pl.com.bottega.cms.application.MovieDto" +
                "( m ) " +
                "FROM Movie m " +
                //"LEFT JOIN FETCH m.shows  " +
                "JOIN m.shows s " +
                "JOIN m.actors a " +
                "JOIN m.genres g " +
                "WHERE s.cinema = :cinema AND s.date BETWEEN :fromTime AND :toTime " +
                "GROUP BY m.id ");
        query.setParameter("cinema", cinema);
        query.setParameter("fromTime", date.atStartOfDay());
        query.setParameter("toTime", date.atTime(23, 59));
        return query.getResultList();
    }

    @Override
    public MovieDto get(Long movieId) {
        Movie movie = entityManager.find(Movie.class, movieId);
        if (movie == null)
            throw new NoSuchEntityException();
        return new MovieDto(movie);
    }
}
