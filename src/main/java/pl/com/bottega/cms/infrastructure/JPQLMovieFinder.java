package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.MovieFinder;
import pl.com.bottega.cms.application.ShowDto;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;

@Component
@Transactional
public class JPQLMovieFinder implements MovieFinder {

    private EntityManager entityManager;

    public JPQLMovieFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<MovieDto> getFromDay(Long cinemaId, LocalDate date) {
        //TODO poprawić żeby nie podciągały się wszystkie show a tylko z danego dnia!!!!!
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
                "GROUP BY m.id " +
                "ORDER BY m.title ASC");
        query.setParameter("cinema", cinema);
        query.setParameter("fromTime", date.atTime(LocalTime.MIDNIGHT));
        query.setParameter("toTime", date.atTime(LocalTime.MAX) );
        List<MovieDto> result = query.getResultList();
        for (MovieDto m : result){
           Movie movie = entityManager.find(Movie.class, m.getId());
            m.addShows(getShowsFromDay(movie, date));
        }
        return result;
    }

    private List<ShowDto> getShowsFromDay(Movie movie, LocalDate date) {
        Query query = entityManager.createQuery("SELECT NEW " +
                "pl.com.bottega.cms.application.ShowDto (s ) " +
                "FROM Show s " +
                "WHERE s.date BETWEEN :fromTime AND :toTime AND s.movie = :movie " +
                "ORDER BY s.date ASC");
        query.setParameter("fromTime", date.atTime(LocalTime.MIDNIGHT));
        query.setParameter("toTime", date.atTime(LocalTime.MAX) );
        query.setParameter("movie", movie);
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
