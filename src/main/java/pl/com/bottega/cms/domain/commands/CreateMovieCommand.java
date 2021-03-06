package pl.com.bottega.cms.domain.commands;

import java.util.Set;

public class CreateMovieCommand implements Command {

    private String title;

    private String description;

    private Set<String> actors;

    private Set<String> genres;

    private Integer minAge;

    private Integer length;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void validate(ValidationErrors errors) {
        validatePresence(errors, "title", title);
        validatePresence(errors, "description", description);
        validatePresence(errors, "actors", actors);
        validatePresence(errors, "genres", genres);
        validatePresence(errors, "minAge", minAge);
        validatePresence(errors, "length", length);
    }
}
