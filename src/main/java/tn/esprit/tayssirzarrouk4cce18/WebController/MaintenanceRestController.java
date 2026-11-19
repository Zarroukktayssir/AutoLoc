package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Maintenance;
import tn.esprit.tayssirzarrouk4cce18.Service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@Tag(name = "Maintenance", description = "API de gestion des maintenances")
public class MaintenanceRestController {

    private final MaintenanceService maintenanceService;

    public MaintenanceRestController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    @Operation(summary = "Lister les maintenance")
    public List<Maintenance> findAll() {
        return maintenanceService.getAllMaintenances();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un maintenance par identifiant")
    public ResponseEntity<Maintenance> findById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un maintenance")
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance maintenance) {
        maintenance.setIdMaintenance(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.saveMaintenance(maintenance));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un maintenance")
    public ResponseEntity<Maintenance> update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        if (maintenanceService.getMaintenanceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        maintenance.setIdMaintenance(id);
        return ResponseEntity.ok(maintenanceService.saveMaintenance(maintenance));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un maintenance par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (maintenanceService.getMaintenanceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}


