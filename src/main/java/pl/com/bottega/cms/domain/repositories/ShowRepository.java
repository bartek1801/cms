package pl.com.bottega.cms.domain.repositories;

import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.Show;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository {

    void save(Show show);

    List find(LocalDateTime date, Cinema cinema, Movie movie);

    Show get(Long showNo);
}
