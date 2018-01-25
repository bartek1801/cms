package pl.com.bottega.cms.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.application.CinemaFinder;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FindAllCinemasTest extends AcceptanceTest {


    @Autowired
    private CinemaFinder cinemaFinder;

    @Autowired
    private EntityManager entityManager;

    private CreateCinemaCommand createCinemaCommand;

    @Before
    public void createCinema(){
        createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setCity("Lublin");
        createCinemaCommand.setName("Plaza");
    }

    @Test
    public void shouldBeEmptyWhenDatabaseIsEmpty(){
        //when
        List<CinemaDto> cinemas = cinemaFinder.getAll();

        //then
        assertThat(cinemas.isEmpty());
    }


    @Test
    @Transactional
    public void shouldReturnSomeCinema() {
        //given
        Cinema cinema1 = new Cinema("Plaza", "Lublin");
        entityManager.persist(cinema1);

        //when
        List<CinemaDto> cinemas = cinemaFinder.getAll();

        //then
        assertThat(cinemas.size()).isEqualTo(1);
        assertThat(cinemas.get(0).getCity()).isEqualTo("Lublin");
        assertThat(cinemas.get(0).getName()).isEqualTo("Plaza");
    }


    @Test
    @Transactional
    public void shouldReturnAllCinemas() {
        //given
        Cinema cinema1 = new Cinema("Plaza", "Lublin");
        entityManager.persist(cinema1);
        Cinema cinema2 = new Cinema("Bajkał", "Moskwa");
        entityManager.persist(cinema2);

        //when
        List<CinemaDto> cinemas = cinemaFinder.getAll();

        //then
        assertThat(cinemas.size()).isEqualTo(2);

        assertThat(cinemas.get(0).getName()).isEqualTo("Plaza");
        assertThat(cinemas.get(0).getCity()).isEqualTo("Lublin");
        assertThat(cinemas.get(1).getName()).isEqualTo("Bajkał");
        assertThat(cinemas.get(1).getCity()).isEqualTo("Moskwa");
    }
}