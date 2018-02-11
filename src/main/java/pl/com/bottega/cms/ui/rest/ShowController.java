package pl.com.bottega.cms.ui.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.application.CinemaHallDto;

@RestController
@RequestMapping("/shows")
public class ShowController {


    @GetMapping("{showId}/seats/")
    CinemaHallDto getSeats(@PathVariable Long showId) {
        return null;
    }

}
