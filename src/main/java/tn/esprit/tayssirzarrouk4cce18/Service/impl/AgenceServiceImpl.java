package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Agence;
import tn.esprit.tayssirzarrouk4cce18.Repository.AgenceRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.AgenceService;
import java.util.List;
import java.util.Optional;

@Service
public class AgenceServiceImpl implements AgenceService {
    private final AgenceRepository repository;
    public AgenceServiceImpl(AgenceRepository repository) { this.repository = repository; }
    @Override public List<Agence> getAllAgences() { return repository.findAll(); }
    @Override public Optional<Agence> getAgenceById(Long id) { return repository.findById(id); }
    @Override public Agence saveAgence(Agence agence) { return repository.save(agence); }
    @Override public void deleteAgence(Long id) { repository.deleteById(id); }
}
