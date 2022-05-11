package org.lamisplus.modules.pharmacy.domain.mapper;


import org.lamisplus.modules.pharmacy.domain.dto.DrugDispenseDTO;
import org.lamisplus.modules.pharmacy.domain.entity.DrugDispense;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrugDispenseMapper {

    DrugDispense toDrugDispense(DrugDispenseDTO DrugDispenseDTO);

    DrugDispenseDTO toDrugDispenseDTO(DrugDispense DrugDispense);

    List<DrugDispenseDTO> toDrugDispenseDTOList(List<DrugDispense> DrugDispenses);
}
