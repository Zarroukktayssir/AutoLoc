package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Client;
import tn.esprit.tayssirzarrouk4cce18.Repository.ClientRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.ClientService;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    public ClientServiceImpl(ClientRepository repository) { this.repository = repository; }
    @Override public List<Client> getAllClients() { return repository.findAll(); }
    @Override public Optional<Client> getClientById(Long id) { return repository.findById(id); }
    @Override public Client saveClient(Client client) { return repository.save(client); }
    @Override public void deleteClient(Long id) { repository.deleteById(id); }
}
