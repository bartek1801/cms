package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.Show;

import java.time.LocalTime;

public class ShowDto {

    private Long id;

    private LocalTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ShowDto(Show show) {
        this.id = show.getId();
        this.time = show.getDate().toLocalTime();
    }


}
