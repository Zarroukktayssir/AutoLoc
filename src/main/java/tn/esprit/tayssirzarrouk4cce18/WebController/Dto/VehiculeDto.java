package tn.esprit.tayssirzarrouk4cce18.WebController.Dto;

import java.math.BigDecimal;
import tn.esprit.tayssirzarrouk4cce18.Domain.CategorieVehicule;

public class VehiculeDto {
    String immatriculation;
    String marque;
    String modele;
    CategorieVehicule categorie;
    BigDecimal tarifJournalier;
    String nomAgence;

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public CategorieVehicule getCategorie() { return categorie; }
    public void setCategorie(CategorieVehicule categorie) { this.categorie = categorie; }

    public BigDecimal getTarifJournalier() { return tarifJournalier; }
    public void setTarifJournalier(BigDecimal tarifJournalier) { this.tarifJournalier = tarifJournalier; }

    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }
}
