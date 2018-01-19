package pl.com.bottega.cms.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.application.CreateMovieHandler;
import pl.com.bottega.cms.application.MovieDto;
import pl.com.bottega.cms.application.MovieFinder;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddMovieTest extends AcceptanceTest {

    @Autowired
    private CreateMovieHandler createMovieHandler;

    @Autowired
    private MovieFinder movieFinder;

    private CreateMovieCommand cmd;

    @Before
    public void createMovie(){
        cmd = new CreateMovieCommand();
        cmd.setActors(new HashSet<String>(Arrays.asList("John Travolta")));
        cmd.setDescription("Royale with cheese");
        cmd.setGenres(new HashSet<>(Arrays.asList("Sensacyjny")));
        cmd.setMinAge(17);
        cmd.setTitle("Pulp Fiction");
    }

    @Test
    public void shouldAddMovie() {
        //when
       createMovieHandler.handle(cmd);

       //then
        MovieDto movieDto = movieFinder.get(1L);
        assertEquals(cmd.getActors(), movieDto.getActors());
        assertEquals(cmd.getGenres(), movieDto.getGenres());
        assertEquals("Royale with cheese", movieDto.getDescription());
        assertEquals(cmd.getMinAge(), movieDto.getMinAge());
        assertEquals("Pulp Fiction", movieDto.getTitle());


    }
}
