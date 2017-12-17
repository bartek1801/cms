package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.commands.Command;

public interface Handler<C extends Command> {

    void handle(C command);

    Class<? extends Command> getSupportedCommandClass();

    default boolean canHandle(Command command){
        return command.getClass().equals(getSupportedCommandClass());
    }
}
