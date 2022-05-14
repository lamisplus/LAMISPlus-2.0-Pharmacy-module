package org.lamisplus.modules.pharmacy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DrugOrderDTO {
    private Long id;

    @JsonIgnore
    private String prescriptionGroupId;

    @JsonIgnore
    private String uuid;
    private String drugName;

    //TODO: change to integer
    private String dosageStrengthUnit;
    private String dosageUnit;
    private String comments;
    private String orderedBy;
    private String dosageStrength;

    //TODO: change to integer
    private String duration;
    private Long patientId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    private String durationUnit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimePrescribed;

    private String brand;
    private Integer dosageFrequency;
    private String type;
    @JsonIgnore
    private Integer archived;

    private Object otherDetails;
}
