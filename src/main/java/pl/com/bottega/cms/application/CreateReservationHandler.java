package pl.com.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.domain.CinemaHall;
import pl.com.bottega.cms.domain.Reservation;
import pl.com.bottega.cms.domain.commands.Command;
import pl.com.bottega.cms.domain.commands.CommandInvalidException;
import pl.com.bottega.cms.domain.commands.CreateReservationCommand;
import pl.com.bottega.cms.domain.repositories.ReservationRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class CreateReservationHandler implements Handler<CreateReservationCommand, Long> {


    private ReservationRepository reservationRepository;

    public CreateReservationHandler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateReservationCommand command) {
        //TODO zamiast Long zwrócić np ReservationDto
        Reservation reservation = new Reservation(command);
        Set<Reservation> reservations = reservationRepository.getReservations(command.getShowId());
        CinemaHall cinemaHall = new CinemaHall(reservations);
        cinemaHall.checkReservation(command);

        reservationRepository.save(reservation);

        return reservation .getId();
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }
}
