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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowDto showDto = (ShowDto) o;

        if (!id.equals(showDto.id)) return false;
        return time.equals(showDto.time);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
