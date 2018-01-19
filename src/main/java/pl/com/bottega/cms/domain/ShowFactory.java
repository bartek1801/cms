package pl.com.bottega.cms.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.commands.CreateShowsCommand;
import pl.com.bottega.cms.domain.repositories.CinemaRepository;
import pl.com.bottega.cms.domain.repositories.MovieRepository;
import pl.com.bottega.cms.domain.repositories.ShowRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

@Component
public class ShowFactory {

    private CinemaRepository cinemaRepository;

    private MovieRepository movieRepository;

    private ShowRepository showRepository;

    public ShowFactory(CinemaRepository cinemaRepository, MovieRepository movieRepository, ShowRepository showRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
    }


    @Transactional
    public Collection<Show> createShows(CreateShowsCommand command) {
        Cinema cinema = cinemaRepository.get(command.getCinemaId());
        Movie movie = movieRepository.get(command.getMovieId());
        Collection<Show> showsList = new LinkedList<>();
        if (command.getCalendar() == null) {
            createShowWithoutCalendar(command, cinema, movie, showsList);
        } else {
            LocalDateTime fromDate = command.getCalendar().getFromDate();
            LocalDateTime toDate = command.getCalendar().getUntilDate();
            Set<String> weekDays = command.getCalendar().getWeekDays();
            Set<LocalTime> hours = command.getCalendar().getHours();
            createShowWithCalendar(cinema, movie, showsList, fromDate, toDate, weekDays, hours);
        }
        return showsList;
    }

    private void createShowWithCalendar(Cinema cinema, Movie movie, Collection<Show> showsList,
                                        LocalDateTime fromDate, LocalDateTime toDate,
                                        Set<String> weekDays, Set<LocalTime> hours) {
        for (LocalDateTime date = fromDate; date.isBefore(toDate.plusDays(1L)); date = date.plusDays(1L)) {
            for (String day : weekDays) {
                if (isTheSameDay(date, day)) {
                    for (LocalTime hour : hours) {
                        if (showExist(LocalDateTime.of(date.toLocalDate(), hour), cinema, movie)) {
                            Show show = new Show(cinema, movie, LocalDateTime.of(date.toLocalDate(), hour));
                            showsList.add(show);
                        }
                    }
                }
            }
        }
    }

    private boolean showExist(LocalDateTime dateTime, Cinema cinema, Movie movie) {
        return showRepository.find(dateTime, cinema, movie).isEmpty();
    }//TODO nie trzeba sprawdzać czy show istnieje -> usuń metodę i jej użycia!!!!


    private void createShowWithoutCalendar(CreateShowsCommand command, Cinema cinema, Movie movie, Collection<Show> showsList) {
        command.getDates().stream().forEach(dateTime -> {
            Show show = new Show(cinema, movie, dateTime);
            if (showExist(dateTime, cinema, movie))
                showsList.add(show);
        });
    }

    private boolean isTheSameDay(LocalDateTime date, String day) {
        return date.getDayOfWeek().name().equals(day.toUpperCase());
    }


}
