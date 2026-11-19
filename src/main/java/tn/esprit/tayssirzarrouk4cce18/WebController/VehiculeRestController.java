package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Lister les vehicule")
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
}


