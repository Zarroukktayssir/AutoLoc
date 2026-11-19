package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Employe;
import tn.esprit.tayssirzarrouk4cce18.Service.EmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@Tag(name = "Employe", description = "API de gestion des employes")
public class EmployeRestController {

    private final EmployeService employeService;

    public EmployeRestController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    @Operation(summary = "Lister les employe")
    public List<Employe> findAll() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un employe par identifiant")
    public ResponseEntity<Employe> findById(@PathVariable Long id) {
        return employeService.getEmployeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un employe")
    public ResponseEntity<Employe> create(@RequestBody Employe employe) {
        employe.setIdEmploye(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeService.saveEmploye(employe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employe")
    public ResponseEntity<Employe> update(@PathVariable Long id, @RequestBody Employe employe) {
        if (employeService.getEmployeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employe.setIdEmploye(id);
        return ResponseEntity.ok(employeService.saveEmploye(employe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employe par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (employeService.getEmployeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}


