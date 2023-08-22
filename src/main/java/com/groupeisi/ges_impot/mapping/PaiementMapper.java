package com.groupeisi.ges_impot.mapping;

import com.groupeisi.ges_impot.dto.Paiement;
import com.groupeisi.ges_impot.entities.PaiementEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PaiementMapper {
    Paiement toPaiement(PaiementEntity paiementEntity);
    PaiementEntity fromPaiement(Paiement paiement);
}
