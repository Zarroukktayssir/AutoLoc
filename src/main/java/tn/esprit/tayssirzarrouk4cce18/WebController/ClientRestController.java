package tn.esprit.tayssirzarrouk4cce18.WebController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tayssirzarrouk4cce18.Domain.Client;
import tn.esprit.tayssirzarrouk4cce18.Service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client", description = "API de gestion des clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Lister les client")
    public List<Client> findAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un client par identifiant")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Creer un client")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        client.setIdClient(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(client));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        if (clientService.getClientById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        client.setIdClient(id);
        return ResponseEntity.ok(clientService.saveClient(client));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client par identifiant")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (clientService.getClientById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}


