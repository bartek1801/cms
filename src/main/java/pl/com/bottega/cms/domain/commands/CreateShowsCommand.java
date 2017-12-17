package pl.com.bottega.cms.domain.commands;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateShowsCommand implements Command {

    Long movieId, cinemaId;

    Set<LocalDateTime> dates;

    ShowsCalendar calendar;


    //TODO walidacja komendy mozę być moviID i dates lub movieID i calendar


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Set<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(Set<LocalDateTime> dates) {
        this.dates = dates;
    }

    public ShowsCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(ShowsCalendar calendar) {
        this.calendar = calendar;
    }
}
