package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;

import javax.transaction.Transactional;

@Component
public class CreateReservationHandler implements Handler<CreateReservationCommand> {


    @Override
    //@Transactional
    public void handle(CreateReservationCommand command) {
        //TODO
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }
}
