package tn.esprit.tayssirzarrouk4cce18.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "paiement")
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;
    private BigDecimal montant;
    private LocalDate datePaiement;
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contrat_id", nullable = false)
    @ToString.Exclude
    private Contrat contrat;
}
