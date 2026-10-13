package tn.esprit.tayssirzarrouk4cce18.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "maintenance")
public class Maintenance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaintenance;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicule_id", nullable = false)
    @ToString.Exclude
    private Vehicule vehicule;
}
