package org.lamisplus.modules.pharmacy.repository;


import io.micrometer.core.instrument.Tags;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugOrderRepository extends JpaRepository<DrugOrder, Long> {

    Optional<DrugOrder> findByIdAndArchived(Long id, int archived);

    List<DrugOrder> findAllByArchived(int archived);

    List<DrugOrder> findAllByPatientIdAndArchived(Long patientId, int archived);
    List<DrugOrder> findAllByVisitIdAndArchived(Integer visitId, Integer archived);

    @Query(value = "SELECT * FROM drug_order GROUP BY prescription_group_id, id ORDER BY id DESC", nativeQuery = true)
    List<DrugOrder> findAllDrugOrderGroupByPrescriptionGroupIdOrderById();

    @Query(value = "SELECT * FROM drug_order WHERE patient_id=?1 GROUP BY prescription_group_id, id ORDER BY id DESC", nativeQuery = true)
    List<DrugOrder> findAllByPatientIdGroupByPrescriptionGroupIdOrderById(Long patientId);

    List<DrugOrder> findAllByArchivedAndVisitId(int archived, Long visitId);
}
