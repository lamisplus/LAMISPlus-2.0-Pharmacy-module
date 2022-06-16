package org.lamisplus.modules.pharmacy.repository;


import org.lamisplus.modules.pharmacy.domain.entity.DrugGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugGroupRepository extends JpaRepository<DrugGroup, Long> {
    List<DrugGroup> findAllByArchived(int archived);

    Optional<DrugGroup> findByIdAndArchived(Long id, int archived);
}
