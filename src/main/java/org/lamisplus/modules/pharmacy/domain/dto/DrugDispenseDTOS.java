package org.lamisplus.modules.pharmacy.domain.dto;

import lombok.Data;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import javax.validation.Valid;
import java.util.List;

@Data
public class DrugDispenseDTOS {
    @Valid
    private List<DrugDispense> drugDispenses;
}
