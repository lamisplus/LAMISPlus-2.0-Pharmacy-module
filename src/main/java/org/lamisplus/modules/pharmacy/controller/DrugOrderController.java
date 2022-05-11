package org.lamisplus.modules.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTOS;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.service.DrugOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug-orders")
@Slf4j
@RequiredArgsConstructor
public class DrugOrderController {

    private final DrugOrderService drugOrderService;

    @GetMapping
    public ResponseEntity<List<DrugOrderDTO>> getAllDrugOrders() {
        return ResponseEntity.ok(drugOrderService.getAllDrugOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugOrderDTO> getDrugOrder(@PathVariable Long id) {
        return ResponseEntity.ok(drugOrderService.getDrugOrder(id));
    }

    @PostMapping
    public ResponseEntity<List<DrugOrder>> save(@RequestBody DrugOrderDTOS drugOrderDTOS) {
        return ResponseEntity.ok(drugOrderService.save(drugOrderDTOS));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugOrder> update(@PathVariable Long id, @RequestBody DrugOrderDTO drugOrderDTO) {
        return ResponseEntity.ok(drugOrderService.update(id, drugOrderDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id) {
        return ResponseEntity.ok(drugOrderService.delete(id));
    }
}
