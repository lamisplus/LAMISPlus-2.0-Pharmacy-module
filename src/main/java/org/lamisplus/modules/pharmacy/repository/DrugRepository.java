package org.lamisplus.modules.pharmacy.repository;


import org.lamisplus.modules.pharmacy.domain.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    Optional<Drug> findByName(String name);

    Optional<Drug> findByNameAndArchived(String name, int archived);

    Optional<Drug> findByIdAndArchived(Long id, int archived);

    List<Drug> findAllByArchived(int archived);
}
