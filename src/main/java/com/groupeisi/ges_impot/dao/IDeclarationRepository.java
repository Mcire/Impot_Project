package com.groupeisi.ges_impot.dao;

import com.groupeisi.ges_impot.entities.DeclarationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeclarationRepository extends JpaRepository<DeclarationEntity, Long> {
}
