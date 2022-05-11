package org.lamisplus.modules.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.domain.dto.DrugDispenseDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTOS;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.service.DrugDispenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug-dispenses")
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
    public ResponseEntity<List<DrugDispense>> save(@RequestBody List<DrugDispenseDTO> drugDispensesDTO) {
        return ResponseEntity.ok(drugDispenseService.save(drugDispensesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugDispense> update(@PathVariable Long id, @RequestBody DrugDispenseDTO drugDispenseDTO) {
        return ResponseEntity.ok(drugDispenseService.update(id, drugDispenseDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id) {
        return ResponseEntity.ok(drugDispenseService.delete(id));
    }
}
