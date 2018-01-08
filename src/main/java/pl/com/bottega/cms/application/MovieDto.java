package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.Show;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDto {

    private Long id;

    private String title;

    private String description;

    private Set<String> actors;

    private Set<String> genres;

    private Integer minAge;

    private Integer length;

    private Long showId;

    private LocalTime showTime;

    private Collection<ShowDto> shows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.genres = movie.getGenres();
        this.minAge = movie.getMinAge();
        this.length = movie.getLength();
        //this.shows = movie.getShows().stream().map(ShowDto::new).collect(Collectors.toList());
    }

    public MovieDto( String title, String description, Set<String> actors,
                    Set<String> genres, Integer minAge, Integer length, Collection<Show> shows) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
        this.shows = shows.stream().map(ShowDto::new).collect(Collectors.toList());
    }

}
