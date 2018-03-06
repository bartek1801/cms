package pl.com.bottega.cms.application;

import com.itextpdf.text.DocumentException;
import pl.com.bottega.cms.domain.commands.Command;

import java.io.IOException;

public interface Handler<C extends Command, R> {

    R handle(C command);

    Class<? extends Command> getSupportedCommandClass();

    default boolean canHandle(Command command){
        return command.getClass().equals(getSupportedCommandClass());
    }
}
