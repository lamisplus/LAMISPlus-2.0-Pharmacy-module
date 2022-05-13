package org.lamisplus.modules.pharmacy.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.pharmacy.domain.dto.DrugDispenseDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugDispenseDTOS;
import org.lamisplus.modules.pharmacy.domain.dto.PatientDrugDispenseDTO;
import org.lamisplus.modules.pharmacy.domain.dto.PatientDrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.domain.mapper.DrugDispenseMapper;
import org.lamisplus.modules.pharmacy.repository.DrugDispenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

    public List<DrugDispense> save(DrugDispenseDTOS drugDispenseDTOS) {
        //Optional<Drug> DrugOptional = drugDispenseRepository.findByDrugNameAndPatientIdAnd(drugOrder.getName(), UN_ARCHIVED);
        //if (DrugOptional.isPresent()) throw new RecordExistException(Drug.class, "Name", drugOrder.getName());
        List<DrugDispense> drugDispenseList = new ArrayList<>();
        drugDispenseDTOS.getDrugDispenses().forEach(drugDispense -> {
            if(drugDispense.getDrugOrderId() == null){
                throw new EntityNotFoundException(DrugOrder.class, "DrugOrderId", "DrugOrderId");
            }
            drugDispenseList.add(drugDispense);
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

    public List<PatientDrugDispenseDTO> getAllDrugDispenseForAPatient(Long patientId) {
        Map<Long, List<DrugDispense>> drugDispenseMap = drugDispenseRepository
                .findAllByPatientIdGroupByIdOrderById(patientId)
                .stream()
                .sorted(Comparator.comparingLong(DrugDispense::getDrugOrderId).reversed())
                .collect(groupingBy(DrugDispense::getDrugOrderId));

        return getPatientDrugOrders(drugDispenseMap, patientId);
    }

    public List<PatientDrugDispenseDTO> getAllDrugDispenseForAPatientByDrugOrderId(Long patientId, Long drugOrderId) {
        Map<Long, List<DrugDispense>> drugDispenseMap = drugDispenseRepository
                .findAllDrugDispenseForAPatientByDrugOrderId(patientId, drugOrderId)
                .stream()
                .sorted(Comparator.comparingLong(DrugDispense::getDrugOrderId).reversed())
                .collect(groupingBy(DrugDispense::getDrugOrderId));

        return getPatientDrugOrders(drugDispenseMap, patientId);
    }

    private List<PatientDrugDispenseDTO> getPatientDrugOrders(Map<Long, List<DrugDispense>> drugDispenseMap, Long patientId){
        List<PatientDrugDispenseDTO> patientDrugDispenseDTOS = new ArrayList<>();
        if(patientId != null){
            //get patient id
            //set patient first, last name, hospital number, dob
        }
        drugDispenseMap.forEach((k,v) ->{
            PatientDrugDispenseDTO patientDrugDispenseDTO = new PatientDrugDispenseDTO();
            patientDrugDispenseDTO.setPatientDob(LocalDate.now().minusYears(30L));
            patientDrugDispenseDTO.setPatientFirstName("Test");
            patientDrugDispenseDTO.setPatientLastName("Test");
            patientDrugDispenseDTO.setPatientHospitalNumber("ttttttt");
            patientDrugDispenseDTO.setPatientAddress("White House Abj");
            patientDrugDispenseDTO.setPatientGender("Female");
            patientDrugDispenseDTO.setPatientPhoneNumber("09087654321");

            patientDrugDispenseDTO.setDrugDispenses(v.stream()
                    .map(drugDispense -> {
                        patientDrugDispenseDTO.setPatientId(drugDispense.getPatientId());
                        return drugDispense;
                    })
                    .sorted(Comparator.comparingLong(DrugDispense::getId).reversed())
                    .collect(Collectors.toList()));

            patientDrugDispenseDTOS.add(patientDrugDispenseDTO);
        });


        return patientDrugDispenseDTOS;
    }

    public List<PatientDrugDispenseDTO> getAllDrugDispenseByDrugOrderId(Long drugOrderId) {
        Map<Long, List<DrugDispense>> drugDispenseMap = drugDispenseRepository
                .findAllDrugDispenseByDrugOrderId(drugOrderId)
                .stream()
                .sorted(Comparator.comparingLong(DrugDispense::getDrugOrderId).reversed())
                .collect(groupingBy(DrugDispense::getDrugOrderId));

        return getPatientDrugOrders(drugDispenseMap, null);
    }
}
