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
public class Paiement {

    private Long id;
    @NotNull(message = "La date de payement ne doit en aucun cas etre nulle")
    private Date datePaiement;
    @NotNull(message = "Le montant de payement ne doit en aucun cas etre nulle")
    private double montantPaiement;
    @NotNull(message= "Tout declaration doit avoir un declarant")
    private Long declarationId;

}
