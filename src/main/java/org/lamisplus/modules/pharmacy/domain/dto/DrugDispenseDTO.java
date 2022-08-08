package org.lamisplus.modules.pharmacy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DrugDispenseDTO {
    private Long id;

    @NotBlank(message = "drugName is mandatory")
    private String drugName;
    //private String uuid;

    @NotNull(message = "dateTimeDispensed is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime dateTimeDispensed;
    private String comment;
    private String brand;
    private Long quantity;
    private String unit;
    private String dispensedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private String dosageStrength;
    private String dosageStrengthUnit;
    private Integer dosageFrequency;

    @NotNull(message = "drugOrderId is mandatory")
    private Long drugOrderId;

    @NotNull(message = "patientId is mandatory")
    private Long patientId;
    private Integer duration;
    private String durationUnit;
    private String type;
    private Object otherDetails;
    private Long VisitId;
}
