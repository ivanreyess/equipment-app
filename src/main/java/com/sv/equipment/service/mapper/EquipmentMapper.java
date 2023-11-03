package com.sv.equipment.service.mapper;

import com.sv.equipment.domain.Equipment;
import com.sv.equipment.domain.Job;
import com.sv.equipment.domain.dto.EquipmentDTO;
import com.sv.equipment.domain.dto.JobDTO;
import com.sv.equipment.domain.dto.SimpleJobDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Equipment} and its DTO {@link EquipmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper extends EntityMapper<EquipmentDTO, Equipment> {
    @Mapping(target = "job", source = "job", qualifiedByName = "jobId")
    EquipmentDTO toDto(Equipment s);

    @Named("jobId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SimpleJobDTO toDtoJobId(Job job);
}
