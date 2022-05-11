package org.lamisplus.modules.pharmacy.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTOS;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.domain.mapper.DrugOrderMapper;
import org.lamisplus.modules.pharmacy.repository.DrugOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DrugOrderService {
    private static final int ARCHIVED = 1;
    private static final int UN_ARCHIVED = 0;
    private final DrugOrderRepository drugOrderRepository;
    private final DrugOrderMapper drugOrderMapper;


    public List<DrugOrderDTO> getAllDrugOrders() {
        return drugOrderMapper.toDrugOrderDTOList(drugOrderRepository.findAllByArchived(UN_ARCHIVED));
    }

    public List<DrugOrder> save(DrugOrderDTOS drugOrderDTOS) {
        //Optional<Drug> DrugOptional = drugOrderRepository.findByDrugNameAndPatientIdAnd(drugOrder.getName(), UN_ARCHIVED);
        //if (DrugOptional.isPresent()) throw new RecordExistException(Drug.class, "Name", drugOrder.getName());
        List<DrugOrder> drugOrders = new ArrayList<>();
        String drugPrescribedGroupId = UUID.randomUUID().toString();
        drugOrderDTOS.getDrugOrders().forEach(drugOrder -> {
            drugOrder.setId(null);
            drugOrder.setPrescriptionGroupId(drugPrescribedGroupId);
            drugOrder.setUuid(UUID.randomUUID().toString());
            drugOrders.add(drugOrder);
        });
        return drugOrderRepository.saveAll(drugOrders);
    }

    public DrugOrderDTO getDrugOrder(Long id) {
        DrugOrder drugOrder = drugOrderRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(DrugOrder.class, "Id", id + ""));
        return drugOrderMapper.toDrugOrderDTO(drugOrder);
    }

    public DrugOrder update(Long id, DrugOrderDTO drugOrderDTO) {
        drugOrderRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(DrugOrder.class, "Id", id + ""));

        drugOrderDTO.setId(id);
        DrugOrder drugOrder = drugOrderMapper.toDrugOrder(drugOrderDTO);
        return drugOrderRepository.save(drugOrder);
    }

    public Integer delete(Long id) {
        DrugOrder drugOrder = drugOrderRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(DrugOrder.class, "Id", id + ""));

        drugOrder.setArchived(ARCHIVED);
        //Archive also the drug dispense
        drugOrderRepository.save(drugOrder);
        return drugOrder.getArchived();
    }
}
