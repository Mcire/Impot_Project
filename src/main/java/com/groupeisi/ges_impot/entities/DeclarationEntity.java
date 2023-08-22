package com.groupeisi.ges_impot.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date dateDeclaration;
    @Column
    private double montantDeclaration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "declarantId")
    private DeclarantEntity declarant;

    @OneToMany(mappedBy = "declaration")
    private List<PaiementEntity> paiements;
}
