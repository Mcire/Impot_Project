package com.groupeisi.ges_impot.mapping;

import com.groupeisi.ges_impot.dto.Declarant;
import com.groupeisi.ges_impot.entities.DeclarantEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarantMapper {
    Declarant toDeclarant(DeclarantEntity declarantEntity);
    DeclarantEntity fromDeclarant(Declarant declarant);
}
