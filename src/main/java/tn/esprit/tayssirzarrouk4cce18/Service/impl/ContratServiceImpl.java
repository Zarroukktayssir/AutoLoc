package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Contrat;
import tn.esprit.tayssirzarrouk4cce18.Repository.ContratRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.ContratService;
import java.util.List;
import java.util.Optional;

@Service
public class ContratServiceImpl implements ContratService {
    private final ContratRepository repository;
    public ContratServiceImpl(ContratRepository repository) { this.repository = repository; }
    @Override public List<Contrat> getAllContrats() { return repository.findAll(); }
    @Override public Optional<Contrat> getContratById(Long id) { return repository.findById(id); }
    @Override public Contrat saveContrat(Contrat contrat) { return repository.save(contrat); }
    @Override public void deleteContrat(Long id) { repository.deleteById(id); }
}
