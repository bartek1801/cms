package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CreateMovieCommand;

public class CreateMovieHandler implements Handler<CreateMovieCommand> {

     void handle() {}

     @Override
     public void handle(CreateMovieCommand command) {

     }

     @Override
     public Class<? extends Command> getSupportedCommandClass() {
          return null;
     }
}
