package tn.esprit.tayssirzarrouk4cce18.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "equipement")
public class Equipement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;
    private String libelle;

    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    @JsonIgnore @ToString.Exclude @Builder.Default
    private List<Vehicule> vehicules = new ArrayList<>();
}
