package com.groupeisi.ges_impot.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declarant {

    private Long id;
    @NotNull(message = "La raison ne doit pas etre null")
    private String raisonSociale;
    @NotNull(message = "L'adresse ne doit pas etre null")
    private String adresse;
    @NotNull(message = "L'email ne doit pas etre null")
    private String email;
    @NotNull(message = "Le numero de telephone ne doit pas etre null")
    private String telephone;
}
