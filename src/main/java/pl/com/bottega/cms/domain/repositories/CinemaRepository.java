package pl.com.bottega.cms.domain.repositories;

import pl.com.bottega.cms.application.CinemaDto;
import pl.com.bottega.cms.domain.Cinema;

import java.util.Optional;

/**
 * Created by user on 16.12.2017.
 */
public interface CinemaRepository {

    void save(Cinema cinema);

    boolean isCinemaExist(String name, String city);

    Optional<Cinema> get(String name, String city);


    Cinema get(Long cinemaId);
}
