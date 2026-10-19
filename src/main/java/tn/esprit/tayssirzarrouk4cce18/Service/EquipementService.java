package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Equipement;
import java.util.List;
import java.util.Optional;

public interface EquipementService {
    List<Equipement> getAllEquipements();
    Optional<Equipement> getEquipementById(Long id);
    Equipement saveEquipement(Equipement equipement);
    void deleteEquipement(Long id);
}
