package pl.com.bottega.cms.application;

import java.util.List;

public interface ReservationFinder {

    List<ReservationDto> search(ReservationQuery query);
}
