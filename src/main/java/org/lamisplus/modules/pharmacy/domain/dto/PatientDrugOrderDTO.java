package org.lamisplus.modules.pharmacy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import java.time.LocalDate;
import java.util.List;

@Data
public class PatientDrugOrderDTO {
    //Patient details
    private String patientFirstName;
    private String patientLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate PatientDob;
    private String patientHospitalNumber;
    private Long patientId;
    private String patientAddress;
    private String patientPhoneNumber;
    private String patientGender;

    private List<DrugOrder> drugOrders;
}
