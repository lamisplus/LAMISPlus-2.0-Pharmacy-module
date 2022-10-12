package org.lamisplus.modules.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.domain.dto.*;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.service.DrugDispenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/drug-dispenses")
@Slf4j
@RequiredArgsConstructor
public class DrugDispenseController {

    private final DrugDispenseService drugDispenseService;

    @GetMapping
    public ResponseEntity<List<DrugDispenseDTO>> getAllDrugDispenseOrders() {
        return ResponseEntity.ok(drugDispenseService.getAllDrugDispense());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDispenseDTO> getDrugDispense(@PathVariable Long id) {
        return ResponseEntity.ok(drugDispenseService.getDrugDispense(id));
    }

    @PostMapping
    public ResponseEntity<List<DrugDispense>> save(@RequestBody @Valid DrugDispenseDTOS drugDispenseDTOS) {
        return ResponseEntity.ok(drugDispenseService.save(drugDispenseDTOS));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugDispense> update(@PathVariable Long id, @RequestBody DrugDispenseDTO drugDispenseDTO) {
        return ResponseEntity.ok(drugDispenseService.update(id, drugDispenseDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id) {
        return ResponseEntity.ok(drugDispenseService.delete(id));
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<List<PatientDrugDispenseDTO>> getAllDrugDispenseForAPatient(@PathVariable Long id) {
        return ResponseEntity.ok(drugDispenseService.getAllDrugDispenseForAPatient(id));
    }

    @GetMapping("/patients/{id}/{drugOrderId}")
    public ResponseEntity<List<PatientDrugDispenseDTO>> getAllDrugDispenseForAPatientByDrugOrderId(@PathVariable Long id, @PathVariable Long drugOrderId) {
        return ResponseEntity.ok(drugDispenseService.getAllDrugDispenseForAPatientByDrugOrderId(id, drugOrderId));
    }

    @GetMapping("/drug-orders/{id}")
    public ResponseEntity<List<PatientDrugDispenseDTO>> getAllDrugDispenseByDrugOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(drugDispenseService.getAllDrugDispenseByDrugOrderId(id));
    }
}
