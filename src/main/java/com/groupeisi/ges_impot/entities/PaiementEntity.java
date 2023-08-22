package com.groupeisi.ges_impot.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaiementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date datePaiement;
    @Column
    private double montantPaiement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "declarationId")
    private DeclarationEntity declaration;
}
