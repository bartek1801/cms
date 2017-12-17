package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by user on 17.12.2017.
 */
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @ElementCollection
    private Set<String> actors;

    @ElementCollection
    private Set<String> genres;

    @Column(name = "min_age")
    private Integer minAge;

    private Integer length;

    Movie() {}

    
}
