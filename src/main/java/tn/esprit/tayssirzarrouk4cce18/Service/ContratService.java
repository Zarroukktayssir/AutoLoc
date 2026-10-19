package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Contrat;
import java.util.List;
import java.util.Optional;

public interface ContratService {
    List<Contrat> getAllContrats();
    Optional<Contrat> getContratById(Long id);
    Contrat saveContrat(Contrat contrat);
    void deleteContrat(Long id);
}
