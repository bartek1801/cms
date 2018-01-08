package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.MovieFinder;
import pl.com.bottega.cms.domain.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Component
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
                "NEW pl.com.bottega.cms.application.MovieDto" +
                "( m.title, m.description, m.actors, m.genres, m.minAge, m.length, m.shows) " +
                "FROM Movie m " +
                "JOIN FETCH m.shows " +
                "JOIN m.shows s " +
                "WHERE s.cinema = :cinema AND s.date BETWEEN :fromTime AND :toTime ");
        query.setParameter("cinema", cinema);
        query.setParameter("fromTime", date.atStartOfDay());
        query.setParameter("toTime", date.atTime(23, 59));
        return query.getResultList();
    }
}
