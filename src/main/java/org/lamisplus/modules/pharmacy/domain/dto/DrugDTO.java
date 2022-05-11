package org.lamisplus.modules.pharmacy.domain.dto;

import lombok.Data;


@Data
public class DrugDTO {

    private Long id;

    private String name;

    private String code;

    private String abbrev;

    private Long drugGroupId;
}
