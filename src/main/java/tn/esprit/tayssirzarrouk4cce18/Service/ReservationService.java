package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(Long id);
    Reservation saveReservation(Reservation reservation);
    void deleteReservation(Long id);
}
