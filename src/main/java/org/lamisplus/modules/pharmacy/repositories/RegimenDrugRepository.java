package org.lamisplus.modules.pharmacy.repositories;

import org.lamisplus.modules.pharmacy.domain.entity.RegimenDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegimenDrugRepository extends JpaRepository<RegimenDrug, Long> {

    List<RegimenDrug> findAllByRegimenId(Long regimenId);
}
