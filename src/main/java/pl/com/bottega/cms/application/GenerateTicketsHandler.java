package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.GenerateTicketsCommand;
import pl.com.bottega.cms.domain.repositories.Repository;

public class GenerateTicketsHandler implements Handler<GenerateTicketsCommand, Void> {

    private Repository<Reservation> repository;

    public GenerateTicketsHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(GenerateTicketsCommand command) { //TODO method should return byte[]
        Reservation reservation = repository.get(command.getReservationNumber());

        return null;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return GenerateTicketsCommand.class;
    }
}
