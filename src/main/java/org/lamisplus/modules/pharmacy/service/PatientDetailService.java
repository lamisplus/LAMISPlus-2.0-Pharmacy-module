package org.lamisplus.modules.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.patient.domain.dto.PersonResponseDto;
import org.lamisplus.modules.patient.service.PersonService;
import org.lamisplus.modules.pharmacy.domain.dto.PatientDetailDTO;
import org.lamisplus.modules.pharmacy.domain.dto.PatientDrugDispenseDTO;
import org.lamisplus.modules.pharmacy.util.JsonNodeTransformer;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientDetailService {
    private final PersonService personService;
    private final JsonNodeTransformer jsonNodeTransformer;


    public PatientDetailDTO setDTO(Long patientId){
        PatientDetailDTO patientDetailDTO = new PatientDetailDTO();
        PersonResponseDto personResponseDTO = personService.getPersonById(patientId);
        try {
            patientDetailDTO.setPatientId(patientId);
            patientDetailDTO.setPatientDob(personResponseDTO.getDateOfBirth());
            patientDetailDTO.setPatientLastName(personResponseDTO.getSurname());
            patientDetailDTO.setPatientFirstName(personResponseDTO.getFirstName());
            patientDetailDTO
                    .setPatientHospitalNumber(jsonNodeTransformer
                            .getNodeValue(personResponseDTO
                                    .getIdentifier(), "identifier", "value", true));

            patientDetailDTO
                    .setPatientAddress(jsonNodeTransformer
                            .getNodeValue(personResponseDTO
                                    .getAddress(), "address", "city", true));

            patientDetailDTO
                    .setPatientGender(jsonNodeTransformer
                            .getNodeValue(personResponseDTO
                                    .getGender(), null, "display", false));

            patientDetailDTO
                    .setPatientPhoneNumber(jsonNodeTransformer
                            .getNodeValue(personResponseDTO
                                            .getContactPoint(),
                                    "contactPoint", "value", true));
        }catch (Exception e){
            e.printStackTrace();
        }
        return patientDetailDTO;
    }
}
