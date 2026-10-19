package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Maintenance;
import tn.esprit.tayssirzarrouk4cce18.Repository.MaintenanceRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.MaintenanceService;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository repository;
    public MaintenanceServiceImpl(MaintenanceRepository repository) { this.repository = repository; }
    @Override public List<Maintenance> getAllMaintenances() { return repository.findAll(); }
    @Override public Optional<Maintenance> getMaintenanceById(Long id) { return repository.findById(id); }
    @Override public Maintenance saveMaintenance(Maintenance maintenance) { return repository.save(maintenance); }
    @Override public void deleteMaintenance(Long id) { repository.deleteById(id); }
}
