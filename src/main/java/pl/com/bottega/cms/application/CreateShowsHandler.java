package pl.com.bottega.cms.application;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cms.domain.Show;
import pl.com.bottega.cms.domain.ShowFactory;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CreateShowsCommand;
import pl.com.bottega.cms.domain.repositories.ShowRepository;

import java.util.function.Consumer;

@Component
@Transactional
public class CreateShowsHandler implements Handler<CreateShowsCommand> {

    private ShowRepository showRepository;

    private ShowFactory showFactory;

    public CreateShowsHandler(ShowRepository showRepository, ShowFactory showFactory) {
        this.showRepository = showRepository;
        this.showFactory = showFactory;
    }


    @Override
    public void handle(CreateShowsCommand command) {
        showFactory.createShows(command).stream().forEach(show -> showRepository.save(show));
    }

    public Class<? extends Command> getSupportedCommandClass() {
        return CreateShowsCommand.class;
    }
}
