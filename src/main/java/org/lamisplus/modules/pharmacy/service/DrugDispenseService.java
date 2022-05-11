package org.lamisplus.modules.pharmacy.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.pharmacy.domain.dto.DrugDispenseDTO;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.domain.mapper.DrugDispenseMapper;
import org.lamisplus.modules.pharmacy.repository.DrugDispenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DrugDispenseService {
    private static final int ARCHIVED = 1;
    private static final int UN_ARCHIVED = 0;
    private final DrugDispenseRepository drugDispenseRepository;
    private final DrugDispenseMapper drugDispenseMapper;


    public List<DrugDispenseDTO> getAllDrugDispense() {
        return drugDispenseMapper.toDrugDispenseDTOList(drugDispenseRepository.findAllByArchived(UN_ARCHIVED));
    }

    public List<DrugDispense> save(List<DrugDispenseDTO> drugDispenseDTOS) {
        //Optional<Drug> DrugOptional = drugDispenseRepository.findByDrugNameAndPatientIdAnd(drugOrder.getName(), UN_ARCHIVED);
        //if (DrugOptional.isPresent()) throw new RecordExistException(Drug.class, "Name", drugOrder.getName());
        List<DrugDispense> drugDispenseList = new ArrayList<>();
        String drugPrescribedGroupId = UUID.randomUUID().toString();
        drugDispenseDTOS.forEach(drugDispense -> {
            if(drugDispense.getDrugOrderId() == null){
                throw new EntityNotFoundException(DrugOrder.class, "DrugOrderId", "DrugOrderId");
            }
            drugDispenseList.add(drugDispenseMapper.toDrugDispense(drugDispense));
        });
        return drugDispenseRepository.saveAll(drugDispenseList);
    }

    public DrugDispenseDTO getDrugDispense(Long id) {
        DrugDispense drugDispense = drugDispenseRepository
                .findByIdAndArchived(id, UN_ARCHIVED)
                .orElseThrow(() -> new EntityNotFoundException(DrugDispense.class, "Id", id + ""));
        return drugDispenseMapper.toDrugDispenseDTO(drugDispense);
    }

    public DrugDispense update(Long id, DrugDispenseDTO drugDispenseDTO) {
        drugDispenseRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(DrugDispense.class, "Id", id + ""));

        drugDispenseDTO.setId(id);
        DrugDispense drugDispense = drugDispenseMapper.toDrugDispense(drugDispenseDTO);
        return drugDispenseRepository.save(drugDispense);
    }

    public Integer delete(Long id) {
        DrugDispense drugDispense = drugDispenseRepository
                .findByIdAndArchived(id, UN_ARCHIVED)
                .orElseThrow(() -> new EntityNotFoundException(DrugDispense.class, "Id", id + ""));

        drugDispense.setArchived(ARCHIVED);
        //Archive also the drug dispense
        drugDispenseRepository.save(drugDispense);
        return drugDispense.getArchived();
    }
}
