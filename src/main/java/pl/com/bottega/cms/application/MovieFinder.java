package pl.com.bottega.cms.application;


import java.time.LocalDate;
import java.util.List;

public interface MovieFinder {

    List<MovieDto> getFromDay(Long cinemaId, LocalDate date);
}
