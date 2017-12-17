package pl.com.bottega.cms.application;

import pl.com.bottega.cms.application.CinemaDto;

import java.util.List;

public interface CinemaFinder {

    List<CinemaDto> getAll();
}
