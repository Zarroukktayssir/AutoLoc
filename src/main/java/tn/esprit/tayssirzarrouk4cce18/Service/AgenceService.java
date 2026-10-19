package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Agence;
import java.util.List;
import java.util.Optional;

public interface AgenceService {
    List<Agence> getAllAgences();
    Optional<Agence> getAgenceById(Long id);
    Agence saveAgence(Agence agence);
    void deleteAgence(Long id);
}
