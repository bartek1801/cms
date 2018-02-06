package pl.com.bottega.cms.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.application.CinemaFinder;
import pl.com.bottega.cms.application.CreateCinemaHandler;
import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateCinemaTest extends AcceptanceTest {

    @Autowired
    private CreateCinemaHandler createCinemaHandler;

    @Autowired
    private CinemaFinder cinemaFinder;

    private CreateCinemaCommand createCinemaCommand;

    @Before
    public void createCinema() {
        createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setCity("Lublin");
        createCinemaCommand.setName("Plaza");
    }

    @Test
    public void shouldCreateCinema() {
        //when
        createCinemaHandler.handle(createCinemaCommand);

        //then
        CinemaDto cinemaDto = cinemaFinder.getAll().get(0);
        assertThat(createCinemaCommand.getCity()).isEqualTo(cinemaDto.getCity());
        assertThat(createCinemaCommand.getName()).isEqualTo(cinemaDto.getName());
    }

    @Test(expected = CommandInvalidException.class)
    public void shouldNotAllowToPersistTheSameCinemaTwice() {
        //given
        createCinemaHandler.handle(createCinemaCommand);

        //when
        createCinemaHandler.handle(createCinemaCommand);
    }

    @Test
    public void shouldSaveCinemasAtList() {
        CreateCinemaCommand c1 = new CreateCinemaCommand();
        c1.setName("Olimp");
        c1.setCity("Lublin");
        CreateCinemaCommand c2 = new CreateCinemaCommand();
        c2.setName("Bajka");
        c2.setCity("Lublin");
        CreateCinemaCommand c3 = new CreateCinemaCommand();
        c3.setName("Plaza");
        c3.setCity("Lublin");
        createCinemaHandler.handle(c1);
        createCinemaHandler.handle(c2);
        createCinemaHandler.handle(c3);

        List<CinemaDto> cinemasList = cinemaFinder.getAll();
        assertEquals(3, cinemasList.size());
    }

    @Test(expected = CommandInvalidException.class)
    public void shouldNotAllowToPersistNullParametersAtCinema() {
       createCinemaCommand.setCity("");
       createCinemaCommand.setName("");

       createCinemaHandler.handle(createCinemaCommand);
    }
}