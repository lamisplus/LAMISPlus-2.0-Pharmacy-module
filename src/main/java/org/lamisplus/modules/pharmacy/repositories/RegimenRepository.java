package org.lamisplus.modules.pharmacy.repositories;

import org.lamisplus.modules.pharmacy.domain.entity.Regimen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegimenRepository extends JpaRepository<Regimen, Long> {

    List<Regimen> findAllByRegimenLineIdAndArchived(Long regimenLineId, int archived);

    Optional<Regimen> findByIdAndArchived(Long id, int archived);

    List<Regimen> findAllByArchived(int archived);
}
