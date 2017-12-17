package pl.com.bottega.cms.domain;

import java.util.Collection;

/**
 * Created by user on 17.12.2017.
 */
public class Movie {

    private String title;

    private String description;

    private Collection<Actor> actors;

    private Collection<Genre> genres;

    private String minAge;

    private Integer length;
}
