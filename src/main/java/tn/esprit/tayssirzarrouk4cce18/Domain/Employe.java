package tn.esprit.tayssirzarrouk4cce18.Domain;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "employe")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private RoleEmploye role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agence_id", nullable = false)
    @ToString.Exclude
    private Agence agence;
}
