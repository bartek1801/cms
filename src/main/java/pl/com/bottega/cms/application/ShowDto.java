package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.Show;

import java.time.LocalTime;

public class ShowDto {

    Long id;

    LocalTime time;

    String movie;

    public ShowDto(Show show) {
        this.id = show.getId();
        this.time = show.getDate().toLocalTime();
    }


}
