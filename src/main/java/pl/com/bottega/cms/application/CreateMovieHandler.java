package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;
import pl.com.bottega.cms.domain.repositories.MovieRepository;
@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand> {

     private MovieRepository repository;

     public CreateMovieHandler(MovieRepository repository) {
          this.repository = repository;
     }

     @Transactional
     public void handle(CreateMovieCommand cmd) {
          Movie movie = new Movie(cmd.getTitle(), cmd.getDescription(), cmd.getActors(),
                  cmd.getGenres(), cmd.getMinAge(), cmd.getLength());
          repository.save(movie);
     }


     @Override
     public Class<? extends Command> getSupportedCommandClass() {
          return CreateMovieCommand.class;
     }
}
