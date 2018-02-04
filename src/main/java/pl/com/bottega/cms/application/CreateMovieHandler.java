package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.Movie;
import pl.com.bottega.cms.domain.commands.*;
import pl.com.bottega.cms.domain.repositories.MovieRepository;
@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand, Void> {
     public static final String FIELD_CAN_T_BE_EMPTY_ERROR = "Field can't be empty";
     public static final String FIELD_CAN_T_BE_NEGATIVE = "Field can't be negative";

     private MovieRepository repository;

     public CreateMovieHandler(MovieRepository repository) {
          this.repository = repository;
     }

     @Transactional
     public Void handle(CreateMovieCommand cmd) {
          validateMovieParameters(cmd);
          Movie movie = new Movie(cmd.getTitle(), cmd.getDescription(), cmd.getActors(),
                  cmd.getGenres(), cmd.getMinAge(), cmd.getLength());
          repository.save(movie);
         return null;
     }
     private void validateMovieParameters(CreateMovieCommand command) {
          ValidationErrors errors = new ValidationErrors();
          boolean incorrectParameter = false;

          if(command.getActors().isEmpty()) {
               errors.add("actors", FIELD_CAN_T_BE_EMPTY_ERROR);
               incorrectParameter = true;
          }
          if (command.getGenres().isEmpty()) {
               errors.add("genres", FIELD_CAN_T_BE_EMPTY_ERROR);
               incorrectParameter = true;
          }
          if (command.getMinAge() < 0) {
               errors.add("minAge", FIELD_CAN_T_BE_NEGATIVE);
               incorrectParameter = true;
          }
          if (command.getLength() <= 0) {
               errors.add("lengt", FIELD_CAN_T_BE_NEGATIVE);
               incorrectParameter = true;
          }

          if(incorrectParameter) throw new CommandInvalidException(errors);
     }


     @Override
     public Class<? extends Command> getSupportedCommandClass() {
          return CreateMovieCommand.class;
     }
}
