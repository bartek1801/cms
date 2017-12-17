package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.Cinema;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.com.bottega.cms.domain.commands.ValidationErrors;
import pl.com.bottega.cms.domain.repositories.CinemaRepository;

import javax.transaction.Transactional;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand> {

    private CinemaRepository cinemaRepository;

    public CreateCinemaHandler(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }


    @Override
    @Transactional
    public void handle(CreateCinemaCommand command) {
        validateCinemaPresence(command);
        cinemaRepository.save(new Cinema(command.getName(), command.getCity()));
    }

    private void validateCinemaPresence(CreateCinemaCommand command) {
        if (cinemaRepository.isCinemaExist(command.getName(), command.getCity())){
            ValidationErrors errors = new ValidationErrors();
            errors.add("cinema", "Cinema already exist");
            throw new CommandInvalidException(errors);
        }
    }


    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }
}
