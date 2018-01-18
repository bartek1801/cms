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
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class JPQLMovieFinder implements MovieFinder {

    private EntityManager entityManager;

    public JPQLMovieFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<MovieDto> getFromDay(Long cinemaId, LocalDate date) {
        Cinema cinema = entityManager.find(Cinema.class, cinemaId);
        Query query = entityManager.createQuery("SELECT " +
                "DISTINCT ( m ) " +
                "FROM Movie m " +
                "JOIN FETCH m.shows s " +
                "JOIN FETCH m.actors " +
                "JOIN FETCH m.genres " +
                "WHERE s.cinema = :cinema AND s.date BETWEEN :fromTime AND :toTime " +
                "ORDER BY m.title ASC, s.date ASC");
        query.setParameter("cinema", cinema);
        query.setParameter("fromTime", date.atTime(LocalTime.MIDNIGHT));
        query.setParameter("toTime", date.atTime(LocalTime.MAX));
        List<Movie> movies = query.getResultList();
        return movies.stream().map(MovieDto::new).collect(Collectors.toList());
    }


    @Override
    public MovieDto get(Long movieId) {
        Movie movie = entityManager.find(Movie.class, movieId);
        if (movie == null)
            throw new NoSuchEntityException();
        return new MovieDto(movie);
    }
}
