package org.lamisplus.modules.pharmacy.domain.dto;

import lombok.Data;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import java.util.List;

@Data
public class DrugOrderDTOS {
    private List<DrugOrder> drugOrders;
}
