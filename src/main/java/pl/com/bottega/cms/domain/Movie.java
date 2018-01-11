package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.util.*;

/**
 * Created by user on 17.12.2017.
 */
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

    @OneToMany(mappedBy = "movie")
    private Collection<Show> shows = new LinkedList<>();

    public Movie(String title, String description, Set<String> actors, Set<String> genres,
                 Integer minAge, Integer length) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
    }

    Movie() {}

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

    public Collection<Show> getShows() {
        return shows;
    }
}
