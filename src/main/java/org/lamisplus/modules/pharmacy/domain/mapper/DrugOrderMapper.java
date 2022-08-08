package org.lamisplus.modules.pharmacy.domain.mapper;


import org.lamisplus.modules.pharmacy.domain.dto.DrugOrderDTO;
import org.lamisplus.modules.pharmacy.domain.entity.DrugOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrugOrderMapper {

    DrugOrder toDrugOrder(DrugOrderDTO drugOrderDTO);

    DrugOrderDTO toDrugOrderDTO(DrugOrder drugOrder);

    List<DrugOrderDTO> toDrugOrderDTOList(List<DrugOrder> drugOrders);

    List<DrugOrder> toDrugOrderList(List<DrugOrderDTO> drugOrdersDTO);
}
