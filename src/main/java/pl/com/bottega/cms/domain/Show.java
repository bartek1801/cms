package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.ShowsCalendar;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    private LocalDateTime date;


    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDateTime date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }

    public Show(Long cinemaId, Long movieId, LocalDateTime dateTime) {

    }

}
