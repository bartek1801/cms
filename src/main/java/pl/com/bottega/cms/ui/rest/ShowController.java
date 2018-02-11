package pl.com.bottega.cms.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.application.CinemaHallDto;
import pl.com.bottega.cms.infrastructure.JPACinemaFinder;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private JPACinemaFinder finder;

    public ShowController(JPACinemaFinder finder) {
        this.finder = finder;
    }

    @GetMapping("/{showId}/seats")
    public CinemaHallDto getSeats(@PathVariable Long showId) {
        return finder.getSeats(showId);
    }

}
