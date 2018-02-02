package pl.com.bottega.cms.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.MovieFinder;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.Java6Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FindAllMoviesTest extends AcceptanceTest {


    @Autowired
    private MovieFinder movieFinder;

    @Autowired
    private EntityManager entityManager;

    private CreateMovieCommand createMovieCommand;

    @Before
    public void createMovie() {
        createMovieCommand = new CreateMovieCommand();
        createMovieCommand.setTitle("Pulp Fiction");
        createMovieCommand.setDescription("Royale with cheese");
        createMovieCommand.setActors(new HashSet<>(Arrays.asList("John Travolta")));
        createMovieCommand.setGenres(new HashSet<>(Arrays.asList("Sensacyjny")));
        createMovieCommand.setMinAge(17);
        createMovieCommand.setLength(120);
    }

    @Test
    public void shouldBeEmptyWhenDatabaseIsEmpty() {
        //when
        List<MovieDto> movies = movieFinder.getFromDay((long) 1, (LocalDate.parse("2017-12-28")));

        //then
        assertThat(movies.isEmpty());
    }
//TODO 
//    @Test
//    @Transactional
//    public void shouldReturnSomeMoviesOnAGivenDay() {
//        //given
//
//        Set<String> actors = new HashSet<String>();
//        actors.add("John Travolta");
//
//        Set<String> genres = new HashSet<String>();
//        genres.add("Sensacyjny");
//
//        Movie movie1 = new Movie("Pulp Fiction", "Royale with cheese", actors, genres,17, 120);
//
//        entityManager.persist(movie1);
//
//        //when
//        List<MovieDto> movies = movieFinder.getFromDay(Long.valueOf(1), (LocalDate.parse("2017-12-28")));
//
//        //then
//        assertThat(movies.size()).isEqualTo(1);
//        assertThat(movies.get(0).getTitle()).isEqualTo("Pulp Fiction");
//        assertThat(movies.get(0).getDescription()).isEqualTo("Royale with cheese");
//        assertThat(movies.get(0).getActors()).isEqualTo(Arrays.asList("John Travolta"));
//        assertThat(movies.get(0).getGenres()).isEqualTo(Arrays.asList("Sensacyjny"));
//        assertThat(movies.get(0).getMinAge()).isEqualTo(Integer.valueOf(17));
//        assertThat(movies.get(0).getLength()).isEqualTo(Integer.valueOf(120));
//    }

//TODO
//    @Test
//    @Transactional
//    public void shouldReturnAllMoviesOnAGivenDay() {
//        //given
//
//        Set<String> actors = new HashSet<String>();
//        actors.add("John Travolta");
//        actors.add("Keanu Reeves");
//
//        Set<String> genres = new HashSet<String>();
//        genres.add("Sensacyjny");
//        genres.add("Dramat");
//
//        Movie movie1 = new Movie("Pulp Fiction", "Royale with cheese", actors, genres,17, 120);
//        entityManager.persist(movie1);
//
//        Movie movie2 = new Movie("John Wick", "Best movie ever", actors, genres, 12, 300);
//        entityManager.persist(movie2);
//
//
//        //when
//        List<MovieDto> movies = movieFinder.getFromDay(Long.valueOf(1), (LocalDate.parse("2017-12-28")));
//
//        //then
//        assertThat(movies.size()).isEqualTo(2);
//
//        assertThat(movies.get(0).getTitle()).isEqualTo("Pulp Fiction");
//        assertThat(movies.get(0).getDescription()).isEqualTo("Royale with cheese");
//        assertThat(movies.get(0).getActors()).isEqualTo("John Travolta");
//        assertThat(movies.get(0).getGenres()).isEqualTo("Sensacyjny");
//        assertThat(movies.get(0).getMinAge()).isEqualTo(17);
//        assertThat(movies.get(0).getLength()).isEqualTo(120);
//        assertThat(movies.get(1).getTitle()).isEqualTo("John Wick");
//        assertThat(movies.get(1).getDescription()).isEqualTo("Best movie ever");
//        assertThat(movies.get(1).getActors()).isEqualTo("Keanu Reeves");
//        assertThat(movies.get(1).getGenres()).isEqualTo("Dramat");
//        assertThat(movies.get(1).getMinAge()).isEqualTo(12);
//        assertThat(movies.get(1).getLength()).isEqualTo(300);
//    }
}
