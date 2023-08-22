package com.groupeisi.ges_impot.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {

    private Long id;
    @NotNull(message = "La date de declaration  ne doit pas etre null")
    private Date dateDeclaration;
    @NotNull(message = "Le montant de declaration ne doit pas etre null")
    private double montantDeclaration;
    @NotNull(message= "Tout declaration doit avoir un declarant")
    private Long declarantId;

}
