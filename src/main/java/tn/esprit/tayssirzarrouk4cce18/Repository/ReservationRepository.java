package tn.esprit.tayssirzarrouk4cce18.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tayssirzarrouk4cce18.Domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> { }
