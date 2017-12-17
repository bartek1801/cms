package pl.com.bottega.cms.application;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.ValidationErrors;

import java.util.Map;
import java.util.Optional;

@Component
public class CommandGateway {

    private ApplicationContext applicationContext; //obiekt springa ktory reprezentuje kontekst aplikacji, mozna pobrac sobie beana
    //z kontekstu springa

    public CommandGateway(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public void execute(Command command){
        validate(command);
        Handler handler = handleFor(command);
        handler.handle(command); }


    private void validate(Command command) {
        ValidationErrors validationErrors = new ValidationErrors();
        command.validate(validationErrors);
        if(validationErrors.any())
            throw new CommandInvalidException(validationErrors);

    }


    private Handler handleFor(Command command) {
        Map<String, Handler> handlers = applicationContext.getBeansOfType(Handler.class);
        Optional<Handler> handlerOptional = handlers.values().stream().filter((h) -> h.canHandle(command)).findFirst();
        return handlerOptional.orElseThrow(() ->
                new IllegalArgumentException("No handler found for "  + command.getClass()));
    }
}
