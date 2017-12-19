package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

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


}
