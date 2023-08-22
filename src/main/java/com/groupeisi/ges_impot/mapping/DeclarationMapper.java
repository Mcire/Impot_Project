package com.groupeisi.ges_impot.mapping;

import com.groupeisi.ges_impot.dto.Declaration;
import com.groupeisi.ges_impot.entities.DeclarationEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarationMapper {
    Declaration toDeclaration(DeclarationEntity declarationEntity);
    DeclarationEntity fromDeclaration(Declaration declaration);
}
