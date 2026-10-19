package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Maintenance;
import java.util.List;
import java.util.Optional;

public interface MaintenanceService {
    List<Maintenance> getAllMaintenances();
    Optional<Maintenance> getMaintenanceById(Long id);
    Maintenance saveMaintenance(Maintenance maintenance);
    void deleteMaintenance(Long id);
}
