package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.tayssirzarrouk4cce18.Domain.Employe;
import tn.esprit.tayssirzarrouk4cce18.Repository.EmployeRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.EmployeService;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final EmployeRepository repository;
    public EmployeServiceImpl(EmployeRepository repository) { this.repository = repository; }
    @Override public List<Employe> getAllEmployes() { return repository.findAll(); }
    @Override public Optional<Employe> getEmployeById(Long id) { return repository.findById(id); }
    @Override public Employe saveEmploye(Employe employe) { return repository.save(employe); }
    @Override public void deleteEmploye(Long id) { repository.deleteById(id); }
}
