package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Contrat;
import tn.esprit.tayssirzarrouk4cce18.Service.ContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrat", description = "API de gestion des contrats")
public class ContratRestController {

    private final ContratService contratService;

    public ContratRestController(ContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    @Operation(summary = "Lister les contrat")
    public List<Contrat> findAll() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un contrat par identifiant")
    public ResponseEntity<Contrat> findById(@PathVariable Long id) {
        return contratService.getContratById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un contrat")
    public ResponseEntity<Contrat> create(@RequestBody Contrat contrat) {
        contrat.setIdContrat(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.saveContrat(contrat));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat contrat) {
        if (contratService.getContratById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contrat.setIdContrat(id);
        return ResponseEntity.ok(contratService.saveContrat(contrat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (contratService.getContratById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }
}


