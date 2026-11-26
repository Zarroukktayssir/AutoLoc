package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Vehicule;
import tn.esprit.tayssirzarrouk4cce18.WebController.Dto.VehiculeDto;

import java.util.List;
import java.util.Optional;

public interface VehiculeService {

    List<Vehicule> getAllVehicules();

    Optional<Vehicule> getVehiculeById(Long id);

    Vehicule saveVehicule(Vehicule vehicule);

    void deleteVehicule(Long id);

    Vehicule assignVehiculeToAgence(Long idVehicule, Long idAgence);

    Vehicule unassignVehiculeToAgence(Long idVehicule, Long idAgence);

    Vehicule assignVehiculeToEquipement(Long idVehicule, Long idEquipement);

    Vehicule unassignVehiculeToEquipement(Long idVehicule, Long idEquipement);

    Vehicule unassignedEquipement(Long idVehicule, Long idEquipement);

    Vehicule addVehiculeAndAssignAgence(Vehicule vehicule, Long idAgence);

    VehiculeDto toDto(Vehicule vehicule);

    VehiculeDto getVehiculeeDtoById(Long id);
}
