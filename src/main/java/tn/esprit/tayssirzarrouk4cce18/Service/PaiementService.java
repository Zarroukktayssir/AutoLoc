package tn.esprit.tayssirzarrouk4cce18.Service;

import tn.esprit.tayssirzarrouk4cce18.Domain.Paiement;
import java.util.List;
import java.util.Optional;

public interface PaiementService {
    List<Paiement> getAllPaiements();
    Optional<Paiement> getPaiementById(Long id);
    Paiement savePaiement(Paiement paiement);
    void deletePaiement(Long id);
}
