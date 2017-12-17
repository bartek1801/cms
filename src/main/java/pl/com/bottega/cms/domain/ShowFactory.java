package pl.com.bottega.cms.domain;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.commands.CreateShowsCommand;
import pl.com.bottega.cms.domain.repositories.CinemaRepository;
import pl.com.bottega.cms.domain.repositories.MovieRepository;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class ShowFactory {

    private CinemaRepository cinemaRepository;

    private MovieRepository movieRepository;

    public ShowFactory(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }


    public Collection<Show> createShows(CreateShowsCommand command){
        Cinema cinema = cinemaRepository.get(command.getCinemaId());
        Movie movie = movieRepository.get(command.getMovieId());
        Collection<Show> showsList = new LinkedList<>();
        if (command.getCalendar() == null) {
            command.getDates().stream().forEach(dateTime -> {
                Show show = new Show(cinema, movie, dateTime);
                showsList.add(show);
            });
        }
        else {
            Show show = new Show(); //new Show(cinema, movie, command.getCalendar()); //TODO
            showsList.add(show);
        }
        return showsList;
    }

}
