package pl.com.bottega.cms.domain;

import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> actors = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> genres = new HashSet<>();

    @Column(name = "min_age")
    private Integer minAge;

    private Integer length;

    @OneToMany //(mappedBy = "movie")
    @JoinColumn(name = "movie_id")
    private Set<Show> shows = new HashSet<>();

    @Embedded
    private TicketPrices ticketPricess;

    public Movie(String title, String description, Set<String> actors, Set<String> genres,
                 Integer minAge, Integer length) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
    }

    public Movie() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getLength() {
        return length;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public TicketPrices getTicketPricess() {
        return ticketPricess;
    }



    public void setPrices(SetTicketPricesCommand command) {
        if(ticketPricess != null)
            ticketPricess.setPrices(command.getPrices());
        else{
            ticketPricess = new TicketPrices(command.getMovieId(), command.getPrices());
        }
    }

    public Receipt calculatePrice(CalculatePricesCommand command) {
        return ticketPricess.calculatePrice(command);
    }
}
