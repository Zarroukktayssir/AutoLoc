package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Agence;
import tn.esprit.tayssirzarrouk4cce18.Service.AgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@Tag(name = "Agence", description = "API de gestion des agences")
public class AgenceRestController {

    private final AgenceService agenceService;

    public AgenceRestController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    @Operation(summary = "Lister les agence")
    public List<Agence> findAll() {
        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un agence par identifiant")
    public ResponseEntity<Agence> findById(@PathVariable Long id) {
        return agenceService.getAgenceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un agence")
    public ResponseEntity<Agence> create(@RequestBody Agence agence) {
        agence.setIdAgence(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.saveAgence(agence));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un agence")
    public ResponseEntity<Agence> update(@PathVariable Long id, @RequestBody Agence agence) {
        if (agenceService.getAgenceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        agence.setIdAgence(id);
        return ResponseEntity.ok(agenceService.saveAgence(agence));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un agence par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (agenceService.getAgenceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        agenceService.deleteAgence(id);
        return ResponseEntity.noContent().build();
    }
}


