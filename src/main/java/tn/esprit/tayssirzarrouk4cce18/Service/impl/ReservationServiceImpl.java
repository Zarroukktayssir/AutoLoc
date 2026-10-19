package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Reservation;
import tn.esprit.tayssirzarrouk4cce18.Repository.ReservationRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.ReservationService;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;
    public ReservationServiceImpl(ReservationRepository repository) { this.repository = repository; }
    @Override public List<Reservation> getAllReservations() { return repository.findAll(); }
    @Override public Optional<Reservation> getReservationById(Long id) { return repository.findById(id); }
    @Override public Reservation saveReservation(Reservation reservation) { return repository.save(reservation); }
    @Override public void deleteReservation(Long id) { repository.deleteById(id); }
}
