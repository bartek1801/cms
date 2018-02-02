package pl.com.bottega.cms.acceptance;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.application.*;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.Show;
import pl.com.bottega.cms.domain.ShowFactory;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;
import pl.com.bottega.cms.domain.commands.CreateShowsCommand;
import pl.com.bottega.cms.domain.commands.ShowsCalendar;
import pl.com.bottega.cms.domain.repositories.MovieRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShowTest extends AcceptanceTest {

    @Autowired
    private MovieFinder movieFinder;

    @Autowired
    private CreateShowsHandler createShowsHandler;

    @Autowired
    private ShowFactory showFactory;

    @Autowired
    private CreateCinemaHandler createCinemaHandler;

    @Autowired
    private CreateMovieHandler createMovieHandler;

    private CreateShowsCommand createShowsCommand = new CreateShowsCommand();

    private Cinema cinema;
    private Movie movie;


    @Before
    @Transactional
    public void setUp(){

        CreateCinemaCommand createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setName("PLaza");
        createCinemaCommand.setCity("Lublin");
        createCinemaHandler.handle(createCinemaCommand);


        CreateMovieCommand createMovieCommand = new CreateMovieCommand();
        createMovieCommand.setActors(new HashSet<>(Arrays.asList("John Travolta")));
        createMovieCommand.setDescription("Royale with cheese");
        createMovieCommand.setGenres(new HashSet<>(Arrays.asList("Sensacyjny")));
        createMovieCommand.setMinAge(17);
        createMovieCommand.setLength(200);
        createMovieCommand.setTitle("Pulp Fiction");
        createMovieHandler.handle(createMovieCommand);

    }


    private CreateShowsCommand createShows(String parameter){
        CreateShowsCommand cmd = new CreateShowsCommand();
        switch (parameter) {
            case "dates":{
                cmd.setCinemaId(1L);
                cmd.setMovieId(1L);
                cmd.setDates(new HashSet<>(Arrays.asList(LocalDateTime.parse("2018-01-27T00:00"),
                                                        LocalDateTime.parse("2018-01-27T12:30"),
                                                        LocalDateTime.parse("2018-01-27T23:59"))));
                return cmd;
            }
            case "calendar": {
                cmd.setCinemaId(1L);
                cmd.setMovieId(1L);
                ShowsCalendar showsCalendar = new ShowsCalendar();
                showsCalendar.setFromDate(LocalDateTime.parse("2018-01-01T10:30:00"));
                showsCalendar.setUntilDate(LocalDateTime.parse("2018-01-15T22:30:00"));
                showsCalendar.setHours(new HashSet<>(Arrays.asList(LocalTime.parse("12:30:00"))));
                showsCalendar.setWeekDays(new HashSet<>(Arrays.asList("Wednesday", "Thursday")));
                cmd.setCalendar(showsCalendar);
                return cmd;
            }
            default:
                return null;
        }
    }


    @Test
    public void shouldGetAllShowsFromADay(){ //TODO (nie numeruje id od  1 ????)
        //given
        CreateShowsCommand createShowsCommand = createShows("dates");
        createShowsHandler.handle(createShowsCommand);

        //when
        List<MovieDto> showsFromDay = movieFinder.getFromDay(1L, LocalDate.parse("2018-01-27"));

        //then
        assertThat(showsFromDay).isNotEmpty();
        assertThat(showsFromDay.get(0).getShows().size()).isEqualTo(3);
    }

    @Test
    public void shouldCreateShowFromDateRequest(){
        //given
        CreateShowsCommand cmd = createShows("dates");

        //when
        Collection<Show> shows = showFactory.createShows(cmd);

        //then
        assertEquals(3, shows.size());
    }

    @Test
    public void shouldCreateShowsFromCalendarRequest(){
        //given
        CreateShowsCommand cmd = createShows("calendar");

        //when
        Collection<Show> shows = showFactory.createShows(cmd);

        //then
        assertEquals(4, shows.size());
    }

}
