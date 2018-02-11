package pl.com.bottega.cms.application;

import pl.com.bottega.cms.domain.PaymentStatus;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.PaymentCommand;
import pl.com.bottega.cms.domain.commands.SetTicketPricesCommand;

public class PaymentHandler implements Handler<SetTicketPricesCommand, Void>{


    @Override
    public Void handle(SetTicketPricesCommand command) {
        return null;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return PaymentCommand.class;
    }
}
