package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Equipement;
import tn.esprit.tayssirzarrouk4cce18.Repository.EquipementRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.EquipementService;
import java.util.List;
import java.util.Optional;

@Service
public class EquipementServiceImpl implements EquipementService {
    private final EquipementRepository repository;
    public EquipementServiceImpl(EquipementRepository repository) { this.repository = repository; }
    @Override public List<Equipement> getAllEquipements() { return repository.findAll(); }
    @Override public Optional<Equipement> getEquipementById(Long id) { return repository.findById(id); }
    @Override public Equipement saveEquipement(Equipement equipement) { return repository.save(equipement); }
    @Override public void deleteEquipement(Long id) { repository.deleteById(id); }
}
