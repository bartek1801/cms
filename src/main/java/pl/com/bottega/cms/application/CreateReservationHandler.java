package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.CinemaHall;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;
import pl.com.bottega.cms.domain.repositories.ReservationRepository;
import pl.com.bottega.cms.domain.repositories.ShowRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class CreateReservationHandler implements Handler<CreateReservationCommand, ReservationNumberDto> {


    private ReservationRepository reservationRepository;

    private ShowRepository showRepository;

    public CreateReservationHandler(ReservationRepository reservationRepository, ShowRepository showRepository) {
        this.reservationRepository = reservationRepository;
        this.showRepository = showRepository;
    }

    @Override
    @Transactional
    public ReservationNumberDto handle(CreateReservationCommand command) {
        Reservation reservation = new Reservation(command);
        //sprawdzić czy istnieja dane rodzaje biletów dla tego show
        //Show show = showRepository ;
        //TODO

        Set<Reservation> reservations = reservationRepository.getReservations(command.getShowId());
        CinemaHall cinemaHall = new CinemaHall(reservations);
        cinemaHall.checkReservation(command);
        reservationRepository.save(reservation);
        return new ReservationNumberDto(reservation);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }
}
