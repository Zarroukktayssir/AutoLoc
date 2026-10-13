package tn.esprit.tayssirzarrouk4cce18.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicule")
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
    private String immatriculation;
    private String marque;
    private String modele;
    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;
    private BigDecimal tarifJournalier;
    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agence_id", nullable = false)
    @ToString.Exclude
    private Agence agence;

    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @Builder.Default
    private List<Maintenance> maintenances = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id"))
    @ToString.Exclude
    @Builder.Default
    private List<Equipement> equipements = new ArrayList<>();

    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();
}
