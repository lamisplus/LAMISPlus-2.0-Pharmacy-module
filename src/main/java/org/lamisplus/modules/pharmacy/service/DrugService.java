package org.lamisplus.modules.pharmacy.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.base.controller.apierror.RecordExistException;
import org.lamisplus.modules.pharmacy.domain.dto.DrugDTO;
import org.lamisplus.modules.pharmacy.domain.entity.Drug;
import org.lamisplus.modules.pharmacy.domain.mapper.DrugMapper;
import org.lamisplus.modules.pharmacy.repository.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DrugService {
    private static final int ARCHIVED = 1;
    private static final int UN_ARCHIVED = 0;
    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;


    public List<DrugDTO> getAllDrugs() {
        return drugMapper.toDrugDTOList(drugRepository.findAllByArchived(UN_ARCHIVED));
    }

    public Drug save(Drug drug) {
        Optional<Drug> DrugOptional = drugRepository.findByNameAndArchived(drug.getName(), UN_ARCHIVED);
        if (DrugOptional.isPresent()) throw new RecordExistException(Drug.class, "Name", drug.getName());
        return drugRepository.save(drug);
    }

    public DrugDTO getDrug(Long id) {
        Drug drug = drugRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(Drug.class, "Id", id + ""));
        return drugMapper.toDrugDTO(drug);
    }

    public Drug update(Long id, DrugDTO drugDTo) {
        drugRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(Drug.class, "Id", id + ""));

        drugDTo.setId(id);
        Drug drug = drugMapper.toDrug(drugDTo);
        return drugRepository.save(drug);
    }

    public Integer delete(Long id) {
        Drug drug = drugRepository.findByIdAndArchived(id, UN_ARCHIVED).orElseThrow(() -> new EntityNotFoundException(Drug.class, "Id", id + ""));

        drug.setArchived(ARCHIVED);
        drugRepository.save(drug);
        return drug.getArchived();
    }

    /*public List<DrugDTO> getDrugsByRegimenId(Long regimenId) {
        List<Drug> drug = regimenDrugRepository.findAllByRegimenId(regimenId).stream().map(RegimenDrug::getDrugByDrugId).collect(Collectors.toList());
        return drugMapper.toDrugDTOList(drug);
    }*/
}
