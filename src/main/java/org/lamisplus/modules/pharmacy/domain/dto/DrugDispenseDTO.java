package org.lamisplus.modules.pharmacy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DrugDispenseDTO {
    private Long id;
    private String drugName;
    //private String uuid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimeDispensed;
    private String comment;
    private String brand;
    private Long quantity;
    private String unit;
    private String dispensedBy;
    private Date startDate;
    private String dosageStrength;
    private String dosageStrengthUnit;
    private Integer dosageFrequency;
    private Long drugOrderId;


    private Long patientId;
    private Integer duration;
    private String durationUnit;
    private String type;

    //Patient details
    private String patientFirstName;
    private String patientLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate PatientDob;
    private String patientHospitalNumber;
    private Object otherDetails;
}
