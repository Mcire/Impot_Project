package com.groupeisi.ges_impot.dao;

import com.groupeisi.ges_impot.entities.DeclarantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeclarantRepository extends JpaRepository<DeclarantEntity, Long> {
}
