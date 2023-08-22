package com.groupeisi.ges_impot.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclarantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false ,length = 225)
    private String raisonSociale;
    @Column(nullable = false, length = 150)
    private String adresse;
    @Column(unique = true , nullable = false , length = 100)
    private String email;
    @Column(unique = true, nullable = false, length = 15)
    private String telephone;

    @OneToMany(mappedBy = "declarant")
    private List<DeclarationEntity> declarations;
}
