package pl.com.bottega.cms.domain.repositories;

import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.Show;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends Repository<Show>{

    List find(LocalDateTime date, Cinema cinema, Movie movie);

}
