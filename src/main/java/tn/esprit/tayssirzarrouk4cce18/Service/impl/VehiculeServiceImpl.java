package tn.esprit.tayssirzarrouk4cce18.Service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tayssirzarrouk4cce18.Domain.Agence;
import tn.esprit.tayssirzarrouk4cce18.Domain.Equipement;
import tn.esprit.tayssirzarrouk4cce18.Domain.Vehicule;
import tn.esprit.tayssirzarrouk4cce18.Repository.AgenceRepository;
import tn.esprit.tayssirzarrouk4cce18.Repository.EquipementRepository;
import tn.esprit.tayssirzarrouk4cce18.Repository.VehiculeRepository;
import tn.esprit.tayssirzarrouk4cce18.Service.VehiculeService;
import tn.esprit.tayssirzarrouk4cce18.WebController.Dto.VehiculeDto;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository,
                               AgenceRepository agenceRepository,
                               EquipementRepository equipementRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
        this.equipementRepository = equipementRepository;
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    public Vehicule saveVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Vehicule assignVehiculeToAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : " + idAgence));
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule unassignVehiculeToAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
        agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : " + idAgence));
        vehicule.setAgence(null);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule assignVehiculeToEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable : " + idEquipement));
        boolean dejaAffecte = vehicule.getEquipements().stream()
                .anyMatch(e -> e.getIdEquipement().equals(idEquipement));
        if (!dejaAffecte) {
            vehicule.getEquipements().add(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule unassignVehiculeToEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable : " + idEquipement));
        boolean dejaAffecte = vehicule.getEquipements().stream()
                .anyMatch(e -> e.getIdEquipement().equals(idEquipement));
        if (dejaAffecte) {
            vehicule.getEquipements().remove(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule unassignedEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable : " + idEquipement));
        // on enlève le fils du parent :
        vehicule.getEquipements().remove(equipement);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule addVehiculeAndAssignAgence(Vehicule vehicule, Long idAgence) {
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable : " + idAgence));
        if (agence != null) {
            vehicule.setAgence(agence);
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public VehiculeDto toDto(Vehicule vehicule) {
        VehiculeDto dto = new VehiculeDto();
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setCategorie(vehicule.getCategorie());
        dto.setTarifJournalier(vehicule.getTarifJournalier());
        if (vehicule.getAgence() != null) {
            dto.setNomAgence(vehicule.getAgence().getNom());
        }
        return dto;
    }

    @Override
    public VehiculeDto getVehiculeeDtoById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);
        return toDto(vehicule);
    }
}
