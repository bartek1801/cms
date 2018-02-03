package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;
import pl.com.bottega.cms.domain.repositories.MovieRepository;

@Component
public class SetTicketPricesHandler implements Handler<SetTicketPricesCommand>{

    private MovieRepository movieRepository;

    public SetTicketPricesHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void handle(SetTicketPricesCommand cmd) {
        Movie movie = movieRepository.get(cmd.getMovieId());
        movie.setPrices(cmd);
        movieRepository.save(movie);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return SetTicketPricesCommand.class;
    }


}
