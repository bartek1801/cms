package pl.com.bottega.cms.domain.repositories;

import pl.com.bottega.cms.domain.Cinema;

import java.util.Optional;

public interface CinemaRepository extends Repository<Cinema> {

    boolean isCinemaExist(String name, String city);

    Optional<Cinema> get(String name, String city);


}
