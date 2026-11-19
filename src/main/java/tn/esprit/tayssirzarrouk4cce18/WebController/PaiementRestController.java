package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Paiement;
import tn.esprit.tayssirzarrouk4cce18.Service.PaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiement", description = "API de gestion des paiements")
public class PaiementRestController {

    private final PaiementService paiementService;

    public PaiementRestController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping
    @Operation(summary = "Lister les paiement")
    public List<Paiement> findAll() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un paiement par identifiant")
    public ResponseEntity<Paiement> findById(@PathVariable Long id) {
        return paiementService.getPaiementById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un paiement")
    public ResponseEntity<Paiement> create(@RequestBody Paiement paiement) {
        paiement.setIdPaiement(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.savePaiement(paiement));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public ResponseEntity<Paiement> update(@PathVariable Long id, @RequestBody Paiement paiement) {
        if (paiementService.getPaiementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        paiement.setIdPaiement(id);
        return ResponseEntity.ok(paiementService.savePaiement(paiement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (paiementService.getPaiementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}


