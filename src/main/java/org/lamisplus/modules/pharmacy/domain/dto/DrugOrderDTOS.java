package org.lamisplus.modules.pharmacy.domain.dto;

import lombok.Data;
import javax.validation.Valid;
import java.util.List;

@Data
public class DrugOrderDTOS {
    @Valid
    private List<DrugOrderDTO> drugOrders;
}
