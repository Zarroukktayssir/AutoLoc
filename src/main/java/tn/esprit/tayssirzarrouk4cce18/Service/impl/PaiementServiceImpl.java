package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Paiement;
import tn.esprit.tayssirzarrouk4cce18.Repository.PaiementRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.PaiementService;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository repository;
    public PaiementServiceImpl(PaiementRepository repository) { this.repository = repository; }
    @Override public List<Paiement> getAllPaiements() { return repository.findAll(); }
    @Override public Optional<Paiement> getPaiementById(Long id) { return repository.findById(id); }
    @Override public Paiement savePaiement(Paiement paiement) { return repository.save(paiement); }
    @Override public void deletePaiement(Long id) { repository.deleteById(id); }
}
