package org.lamisplus.modules.pharmacy.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.pharmacy.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTOS;
import org.lamisplus.modules.pharmacy.domain.dto.PatientDrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.domain.mapper.DrugOrderMapper;
import org.lamisplus.modules.pharmacy.repositories.DrugOrderRepository;
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
public class DrugOrderService {
    private static final int ARCHIVED = 1;
    private static final int UN_ARCHIVED = 0;
    private final DrugOrderRepository drugOrderRepository;
    private final DrugOrderMapper drugOrderMapper;


    public List<DrugOrderDTO> getAllDrugOrders(Long patientId) {
        List<DrugOrderDTO> drugOrdersDTOS;
        if(patientId == 0) {
            drugOrdersDTOS = drugOrderRepository.findAllByArchived(UN_ARCHIVED)
                    .stream()
                    .map(drugOrder -> transformDrugOrder(drugOrder))
                    .collect(Collectors.toList());
        } else {
            drugOrdersDTOS = drugOrderRepository.findAllByPatientIdAndArchived(patientId, UN_ARCHIVED)
                    .stream()
                    .map(drugOrder -> transformDrugOrder(drugOrder))
                    .collect(Collectors.toList());
        }
        return drugOrdersDTOS;

    }

    private  DrugOrderDTO transformDrugOrder(DrugOrder drugOrder){

        if (drugOrder.getDrugDispensesById() == null) {
            drugOrder.setStatus(0);
        } else {
            drugOrder.setDateTimeDispensed(drugOrder.getDrugDispensesById().getDateTimeDispensed());
            drugOrder.setStatus(1);
        }
        return drugOrderMapper.toDrugOrderDTO(drugOrder);

    }

    public List<DrugOrder> save(DrugOrderDTOS drugOrderDTOS) {
        //Optional<Drug> DrugOptional = drugOrderRepository.findByDrugNameAndPatientIdAnd(drugOrder.getName(), UN_ARCHIVED);
        //if (DrugOptional.isPresent()) throw new RecordExistException(Drug.class, "Name", drugOrder.getName());
        List<DrugOrderDTO> drugOrderDTOList = new ArrayList<>();
        String drugPrescribedGroupId = UUID.randomUUID().toString();
        drugOrderDTOS.getDrugOrders().forEach(drugOrderDTO -> {
            if (drugOrderDTO.getPatientId() == null) throw new EntityNotFoundException(DrugOrder.class, "id", "id");
            drugOrderDTO.setId(null);
            drugOrderDTO.setPrescriptionGroupId(drugPrescribedGroupId);
            drugOrderDTO.setUuid(UUID.randomUUID().toString());
            drugOrderDTOList.add(drugOrderDTO);
        });
        return drugOrderRepository.saveAll(drugOrderMapper.toDrugOrderList(drugOrderDTOList));
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

    public List<PatientDrugOrderDTO> getAllDrugOrdersForPatients() {
        Map<String, List<DrugOrder>> orders = drugOrderRepository
                .findAllDrugOrderGroupByPrescriptionGroupIdOrderById()
                .stream()
                .sorted(Comparator.comparingLong(DrugOrder::getId).reversed())
                .collect(groupingBy(DrugOrder::getPrescriptionGroupId));

        return getPatientDrugOrders(orders);
    }

    public List<PatientDrugOrderDTO> getAllDrugOrdersForAPatient(Long patientId) {
        Map<String, List<DrugOrder>> orders = drugOrderRepository
                .findAllByPatientIdGroupByPrescriptionGroupIdOrderById(patientId)
                .stream()
                .sorted(Comparator.comparingLong(DrugOrder::getId).reversed())
                .collect(groupingBy(DrugOrder::getPrescriptionGroupId));

        return getPatientDrugOrders(orders);
    }

    private List<PatientDrugOrderDTO> getPatientDrugOrders(Map<String, List<DrugOrder>> drugOrderMap){
        List<PatientDrugOrderDTO> patientDrugOrderDTOS = new ArrayList<>();
        drugOrderMap.forEach((k,v) ->{
            PatientDrugOrderDTO patientDrugOrderDTO = new PatientDrugOrderDTO();
            patientDrugOrderDTO.setPatientDob(LocalDate.now());
            patientDrugOrderDTO.setPatientFirstName("Emeka");
            patientDrugOrderDTO.setPatientLastName("Greg");
            patientDrugOrderDTO.setPatientHospitalNumber("32542652");
            patientDrugOrderDTO.setPatientAddress("White House Abj");
            patientDrugOrderDTO.setPatientGender("Male");
            patientDrugOrderDTO.setPatientPhoneNumber("09087654321");

            patientDrugOrderDTO.setDrugOrders(v.stream()
                    .map(drugOrder -> {
                        patientDrugOrderDTO.setPatientId(drugOrder.getPatientId());
                        if(drugOrder.getDrugDispensesById() == null){
                            drugOrder.setStatus(0);
                        } else {
                            drugOrder.setDateTimeDispensed(drugOrder.getDrugDispensesById().getDateTimeDispensed());
                            drugOrder.setStatus(1);
                        }
                        return drugOrder;
                    })
                    .sorted(Comparator.comparingLong(DrugOrder::getId).reversed())
                    .collect(Collectors.toList()));

            patientDrugOrderDTOS.add(patientDrugOrderDTO);
        });
        return patientDrugOrderDTOS;
    }
}
