package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Vehicule;
import tn.esprit.tayssirzarrouk4cce18.Service.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicule", description = "API de gestion des vehicules")
public class VehiculeRestController {

    private final VehiculeService vehiculeService;

    public VehiculeRestController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    @Operation(summary = "Lister les vehicules")
    public List<Vehicule> findAll() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un vehicule par identifiant")
    public ResponseEntity<Vehicule> findById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un vehicule")
    public ResponseEntity<Vehicule> create(@RequestBody Vehicule vehicule) {
        vehicule.setIdVehicule(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.saveVehicule(vehicule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un vehicule")
    public ResponseEntity<Vehicule> update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        if (vehiculeService.getVehiculeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehicule.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.saveVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un vehicule par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (vehiculeService.getVehiculeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idVehicule}/agence/{idAgence}")
    @Operation(summary = "Affecter un vehicule a une agence")
    public ResponseEntity<Vehicule> assignVehiculeToAgence(@PathVariable Long idVehicule,
                                                           @PathVariable Long idAgence) {
        try {
            return ResponseEntity.ok(vehiculeService.assignVehiculeToAgence(idVehicule, idAgence));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idVehicule}/agence/{idAgence}/unassign")
    @Operation(summary = "Desaffecter un vehicule d'une agence")
    public ResponseEntity<Vehicule> unassignVehiculeToAgence(@PathVariable Long idVehicule,
                                                             @PathVariable Long idAgence) {
        try {
            return ResponseEntity.ok(vehiculeService.unassignVehiculeToAgence(idVehicule, idAgence));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    @Operation(summary = "Affecter un equipement a un vehicule")
    public ResponseEntity<Vehicule> assignVehiculeToEquipement(@PathVariable Long idVehicule,
                                                               @PathVariable Long idEquipement) {
        try {
            return ResponseEntity.ok(vehiculeService.assignVehiculeToEquipement(idVehicule, idEquipement));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idVehicule}/equipements/{idEquipement}/unassign")
    @Operation(summary = "Desaffecter un equipement d'un vehicule")
    public ResponseEntity<Vehicule> unassignVehiculeToEquipement(@PathVariable Long idVehicule,
                                                                 @PathVariable Long idEquipement) {
        try {
            return ResponseEntity.ok(vehiculeService.unassignVehiculeToEquipement(idVehicule, idEquipement));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/unassignedEquipement/{idVehicule}/{idEquipement}")
    public Vehicule unassignedEquipement(@PathVariable Long idVehicule,
                                         @PathVariable Long idEquipement) {
        return vehiculeService.unassignedEquipement(idVehicule, idEquipement);
    }

    @PostMapping("/addVehiculeAndAssignAgence/{idAgence}")
    public Vehicule addVehiculeAndAssignAgence(@RequestBody Vehicule vehicule,
                                               @PathVariable Long idAgence) {
        return vehiculeService.addVehiculeAndAssignAgence(vehicule, idAgence);
    }
}
