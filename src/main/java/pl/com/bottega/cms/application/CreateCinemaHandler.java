package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CreateCinemaCommand;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand>{

    @Transactional
    public void handle(CreateCinemaCommand cmd) {}

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }
}
