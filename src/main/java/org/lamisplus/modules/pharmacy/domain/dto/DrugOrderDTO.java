package org.lamisplus.modules.pharmacy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank(message = "drugName is mandatory")
    private String drugName;

    //TODO: change to integer
    private String dosageStrengthUnit;
    private String dosageUnit;
    private String comments;
    private String orderedBy;
    private String dosageStrength;

    //TODO: change to integer
    private String duration;

    @NotNull(message = "patientId is mandatory")
    private Long patientId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private String durationUnit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimePrescribed;

    private String brand;
    private Integer dosageFrequency;
    private String type;

    private Object otherDetails;

    @NotNull(message = "encounterDateTime is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime encounterDateTime;

    private Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimeDispensed;
    private Long visitId;
}
