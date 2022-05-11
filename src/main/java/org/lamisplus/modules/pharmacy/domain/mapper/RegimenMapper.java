package org.lamisplus.modules.pharmacy.domain.mapper;


import org.lamisplus.modules.pharmacy.domain.dto.RegimenDTO;
import org.lamisplus.modules.pharmacy.domain.entity.Regimen;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegimenMapper {

    Regimen toRegimen(RegimenDTO regimenDTO);

    RegimenDTO toRegimenDTO(Regimen regimen);

    List<RegimenDTO> toRegimenDTOList(List<Regimen> regimens);
}
