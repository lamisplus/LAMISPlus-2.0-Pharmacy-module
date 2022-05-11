package org.lamisplus.modules.pharmacy.domain.mapper;


import org.lamisplus.modules.pharmacy.domain.dto.DrugDTO;
import org.lamisplus.modules.pharmacy.domain.entity.Drug;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrugMapper {

    Drug toDrug(DrugDTO drugDTO);

    DrugDTO toDrugDTO(Drug drug);

    List<DrugDTO> toDrugDTOList(List<Drug> drugs);
}
