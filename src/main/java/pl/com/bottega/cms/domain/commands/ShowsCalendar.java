package pl.com.bottega.cms.domain.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;


public class ShowsCalendar {

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime fromDate, untilDate;

    private Set<String> weekDays;

    private Set<LocalTime> hours;

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate;
    }

    public void setWeekDays(Set<String> weekDays) {
        this.weekDays = weekDays;
    }

    public void setHours(Set<LocalTime> hours) {
        this.hours = hours;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public Set<String> getWeekDays() {
        return weekDays;
    }

    public Set<LocalTime> getHours() {
        return hours;
    }
}