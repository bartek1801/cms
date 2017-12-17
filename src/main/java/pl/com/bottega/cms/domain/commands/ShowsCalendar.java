package pl.com.bottega.cms.domain.commands;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;


public class ShowsCalendar {

    private LocalDateTime fromdDate, untilDate;

    private Set<String> weekDays;

    private Set<LocalTime> hours;


}