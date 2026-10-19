package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Employe;
import java.util.List;
import java.util.Optional;

public interface EmployeService {
    List<Employe> getAllEmployes();
    Optional<Employe> getEmployeById(Long id);
    Employe saveEmploye(Employe employe);
    void deleteEmploye(Long id);
}
