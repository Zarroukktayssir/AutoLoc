package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Reservation;
import tn.esprit.tayssirzarrouk4cce18.Service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservation", description = "API de gestion des reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Lister les reservation")
    public List<Reservation> findAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un reservation par identifiant")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un reservation")
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        reservation.setIdReservation(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.saveReservation(reservation));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un reservation")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody Reservation reservation) {
        if (reservationService.getReservationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservation.setIdReservation(id);
        return ResponseEntity.ok(reservationService.saveReservation(reservation));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un reservation par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (reservationService.getReservationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}


