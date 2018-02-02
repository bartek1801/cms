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

    public static final String FIELD_CAN_T_BE_EMPTY_ERROR = "Field can't be empty";
    private CinemaRepository cinemaRepository;

    public CreateCinemaHandler(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }


    @Override
    @Transactional
    public void handle(CreateCinemaCommand command) {
        validateCinemaPresence(command);
        validateCinemaParameters(command);
        cinemaRepository.save(new Cinema(command.getName(), command.getCity()));
    }

    private void validateCinemaPresence(CreateCinemaCommand command) {
        if (cinemaRepository.isCinemaExist(command.getName(), command.getCity())){
            ValidationErrors errors = new ValidationErrors();
            errors.add("cinema", "Cinema already exist");
            throw new CommandInvalidException(errors);
        }
    }

    private void validateCinemaParameters(CreateCinemaCommand command) {
        if(command.getCity().isEmpty() || command.getName().isEmpty()) {
            ValidationErrors errors = new ValidationErrors();
            errors.add("name", FIELD_CAN_T_BE_EMPTY_ERROR);
            errors.add("city", FIELD_CAN_T_BE_EMPTY_ERROR);
            throw new CommandInvalidException(errors);
        }
    }


    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }
}
