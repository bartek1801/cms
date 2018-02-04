package pl.com.bottega.cms.domain.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

public class CreateShowsCommand implements Command {

    Long movieId, cinemaId;

    Set<LocalDateTime> dates;

    ShowsCalendar calendar;


    public void validate(ValidationErrors errors){
        validatePresence(errors, "movieId", movieId);

        if (dates == null && calendar == null) {
            errors.add("dates & calendar", "enter dates or calendar");
            throw new CommandInvalidException(errors);
        }
        if (dates != null && calendar != null){
            errors.add("dates & calendar", "enter dates OR calendar, not both");
            throw new CommandInvalidException(errors);
        }
        if (dates!= null && dates.isEmpty()){
            errors.add("dates", "dates can't be empty");
            throw new CommandInvalidException(errors);
        }
        if (calendar != null){
            validatePresence(errors, "calendar: fromDate", calendar.getFromDate());
            validatePresence(errors, "calendar: untilDate", calendar.getUntilDate());
        }
        if (calendar.getWeekDays().isEmpty()){
            errors.add("calendar: weekDays ", "weekDays set is empty");
            throw new CommandInvalidException(errors);
        }
        if (calendar.getHours().isEmpty() || calendar.getHours() == null){
            errors.add("calendar: hours ", "hours set is empty or blank");
            throw new CommandInvalidException(errors);
        }



//        for (String day : calendar.getWeekDays()){
//            if (DayOfWeek.valueOf(day.toUpperCase()) instanceof DayOfWeek) {
//            }
//            else {
//                errors.add("calendar: weekDays ", "weekDays names must be real");
//                throw new CommandInvalidException(errors);
//            }
//        }



    }


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
