package org.lamisplus.modules.pharmacy.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.patient.domain.dto.PersonResponseDto;
import org.lamisplus.modules.patient.service.PersonService;
import org.lamisplus.modules.pharmacy.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTOS;
import org.lamisplus.modules.pharmacy.domain.dto.PatientDrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.lamisplus.modules.pharmacy.domain.mapper.DrugOrderMapper;
import org.lamisplus.modules.pharmacy.repositories.DrugDispenseRepository;
import org.lamisplus.modules.pharmacy.repositories.DrugOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final PersonService personService;
    private final DrugDispenseRepository drugDispenseRepository;

    public List<DrugOrderDTO> getAllDrugOrders(Long patientId) {
        if(!personService.isPersonExist(patientId)){
            throw new org.lamisplus.modules.base.controller.apierror
                    .EntityNotFoundException(DrugOrder.class, "patientId", "" + patientId);
        }

        List<DrugOrderDTO> drugOrdersDTOS;
        if(patientId == 0) {
            drugOrdersDTOS = drugOrderRepository.findAllByArchived(UN_ARCHIVED)
                    .stream()
                    .map(this::transformDrugOrder)
                    .collect(Collectors.toList());
        } else {
            drugOrdersDTOS = drugOrderRepository.findAllByPatientIdAndArchived(patientId, UN_ARCHIVED)
                    .stream()
                    .map(this::transformDrugOrder)
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
        List<DrugOrderDTO> drugOrderDTOList = new ArrayList<>();
        String drugPrescribedGroupId = UUID.randomUUID().toString();

        drugOrderDTOS.getDrugOrders().forEach(drugOrderDTO -> {
            if (drugOrderDTO.getPatientId() == null) throw new EntityNotFoundException(DrugOrder.class, "id", "id");
            if(!personService.isPersonExist(drugOrderDTO.getPatientId())) {
                throw new EntityNotFoundException(DrugOrder.class, "patientId", " " + drugOrderDTO.getPatientId());
            }
            drugOrderDTO.setId(null);
            drugOrderDTO.setPrescriptionGroupId(drugPrescribedGroupId);
            drugOrderDTO.setUuid(UUID.randomUUID().toString());
            drugOrderDTOList.add(drugOrderDTO);
        });
        return drugOrderRepository.saveAll(drugOrderMapper.toDrugOrderList(drugOrderDTOList));
    }

    public DrugOrderDTO getDrugOrder(Long id) {
        DrugOrder drugOrder = drugOrderRepository
                .findByIdAndArchived(id, UN_ARCHIVED)
                .orElseThrow(() -> new EntityNotFoundException(DrugOrder.class, "Id", id + ""));
        return drugOrderMapper.toDrugOrderDTO(drugOrder);
    }

    public DrugOrder update(Long id, DrugOrderDTO drugOrderDTO) {
        drugOrderRepository.findByIdAndArchived(id, UN_ARCHIVED)
                .orElseThrow(() -> new EntityNotFoundException(DrugOrder.class, "Id", id + ""));
        drugOrderDTO.setId(id);
        DrugOrder drugOrder = drugOrderMapper.toDrugOrder(drugOrderDTO);
        return drugOrderRepository.save(drugOrder);
    }

    public Integer delete(Long id) {
        DrugOrder drugOrder = drugOrderRepository
                .findByIdAndArchived(id, UN_ARCHIVED)
                .orElseThrow(() -> new EntityNotFoundException(DrugOrder.class, "Id", id + ""));

        drugOrder.setArchived(ARCHIVED);
        DrugDispense drugDispense = drugOrder.getDrugDispensesById();
        drugDispense.setArchived(ARCHIVED);

        drugOrderRepository.save(drugOrder);
        drugDispenseRepository.save(drugDispense);
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

            patientDrugOrderDTO.setDrugOrders(v.stream()
                    .peek(drugOrder -> {
                        if(patientDrugOrderDTO.getPatientHospitalNumber() == null){
                            PersonResponseDto personResponseDTO = personService.getPersonById(drugOrder.getPatientId());
                            patientDrugOrderDTO.setPatientDob(personResponseDTO.getDateOfBirth());
                            patientDrugOrderDTO.setPatientFirstName(personResponseDTO.getFirstName());
                            patientDrugOrderDTO.setPatientLastName(personResponseDTO.getSurname());
                            patientDrugOrderDTO
                                    .setPatientHospitalNumber(this.getNodeValue(personResponseDTO.getIdentifier(),
                                            "identifier", "value", true));
                            patientDrugOrderDTO.setPatientAddress(this.getNodeValue(personResponseDTO.getAddress(),
                                    "address", "city", true));
                            patientDrugOrderDTO.setPatientGender("Male");
                            patientDrugOrderDTO.setPatientPhoneNumber(this.getNodeValue(personResponseDTO.getContact(),
                                    null, "value", false));
                        }
                        patientDrugOrderDTO.setPatientId(drugOrder.getPatientId());
                        if(drugOrder.getDrugDispensesById() == null){
                            drugOrder.setStatus(0);
                        } else {
                            drugOrder.setDateTimeDispensed(drugOrder.getDrugDispensesById().getDateTimeDispensed());
                            drugOrder.setStatus(1);
                        }
                    })
                    .sorted(Comparator.comparingLong(DrugOrder::getId).reversed())
                    .collect(Collectors.toList()));
            patientDrugOrderDTOS.add(patientDrugOrderDTO);
        });
        return patientDrugOrderDTOS;
    }

    private String getNodeValue(Object obj, String arrayName, String value, Boolean array){
        String jsonStr = obj.toString();
        ObjectMapper mapper = new ObjectMapper();
        if(array) {
            ArrayNode arrayNode;
            try {
                arrayNode = (ArrayNode) mapper.readTree(jsonStr).get(arrayName);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (arrayNode.isArray()) {
                for (JsonNode jsonNode : arrayNode) {
                    if (jsonNode.get("value") != null) {
                        return jsonNode.get("value").asText("none");
                    }
                }
            }
        } else {
            JsonNode jsonNode;
            try {
                jsonNode = mapper.readTree(obj.toString());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return jsonNode.get(value).asText();
        }
        return null;
    }
}
