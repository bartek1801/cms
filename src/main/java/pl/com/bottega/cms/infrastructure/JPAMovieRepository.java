package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.repositories.GenericJPARepository;
import pl.com.bottega.cms.domain.repositories.MovieRepository;

import javax.persistence.EntityManager;

@Component
public class JPAMovieRepository extends GenericJPARepository<Movie> implements MovieRepository {


}
