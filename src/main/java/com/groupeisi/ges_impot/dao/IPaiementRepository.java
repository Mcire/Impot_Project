package com.groupeisi.ges_impot.dao;

import com.groupeisi.ges_impot.entities.PaiementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<PaiementEntity, Long> {
}
