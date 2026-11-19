package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Equipement;
import tn.esprit.tayssirzarrouk4cce18.Service.EquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@Tag(name = "Equipement", description = "API de gestion des equipements")
public class EquipementRestController {

    private final EquipementService equipementService;

    public EquipementRestController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @GetMapping
    @Operation(summary = "Lister les equipement")
    public List<Equipement> findAll() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un equipement par identifiant")
    public ResponseEntity<Equipement> findById(@PathVariable Long id) {
        return equipementService.getEquipementById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un equipement")
    public ResponseEntity<Equipement> create(@RequestBody Equipement equipement) {
        equipement.setIdEquipement(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.saveEquipement(equipement));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un equipement")
    public ResponseEntity<Equipement> update(@PathVariable Long id, @RequestBody Equipement equipement) {
        if (equipementService.getEquipementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        equipement.setIdEquipement(id);
        return ResponseEntity.ok(equipementService.saveEquipement(equipement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un equipement par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (equipementService.getEquipementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}


