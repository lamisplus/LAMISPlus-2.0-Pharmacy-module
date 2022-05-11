package org.lamisplus.modules.pharmacy.domain.dto;

import lombok.Data;

@Data
public class RegimenDTO {

    private Long id;

    private String name;

    private Long regimenLineId;

    private String code;
}
