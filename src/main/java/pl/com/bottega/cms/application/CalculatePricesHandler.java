package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.Receipt;
import pl.com.bottega.cms.domain.Show;
import pl.com.bottega.cms.domain.commands.CalculatePricesCommand;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.repositories.MovieRepository;
import pl.com.bottega.cms.domain.repositories.ShowRepository;

@Component
public class CalculatePricesHandler implements Handler<CalculatePricesCommand, Receipt> {

    private ShowRepository showRepository;


    @Override
    public Receipt handle(CalculatePricesCommand command) {
        Show show = showRepository.get(command.getShowId());

        Receipt receipt = show.calculatePrice(command);
        return receipt;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CalculatePricesCommand.class;
    }
}
