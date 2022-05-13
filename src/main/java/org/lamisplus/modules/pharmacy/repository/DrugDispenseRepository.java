package org.lamisplus.modules.pharmacy.repository;


import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrugDispenseRepository extends JpaRepository<DrugDispense, Long> {

    Optional<DrugDispense> findByIdAndArchived(Long id, int archived);

    List<DrugDispense> findAllByArchived(int archived);

    @Query(value = "SELECT * FROM drug_dispense GROUP BY drug_order_id, id ORDER BY drug_order_id, id DESC", nativeQuery = true)
    List<DrugDispense> findAllDrugOrderGroupByIdOrderById();

    @Query(value = "SELECT * FROM drug_dispense WHERE patient_id=?1 GROUP BY drug_order_id, id ORDER BY id DESC", nativeQuery = true)
    List<DrugDispense> findAllByPatientIdGroupByIdOrderById(Long patientId);

    @Query(value = "SELECT * FROM drug_dispense WHERE patient_id=?1, drug_order_id=?2 GROUP BY drug_order_id, id ORDER BY id DESC", nativeQuery = true)
    List<DrugDispense> findAllDrugDispenseForAPatientByDrugOrderId(Long drugOrderId, Long patientId);

    @Query(value = "SELECT * FROM drug_dispense WHERE drug_order_id=?1 GROUP BY drug_order_id, id ORDER BY id DESC", nativeQuery = true)
    List<DrugDispense>  findAllDrugDispenseByDrugOrderId(Long drugOrderId);
}
