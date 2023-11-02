package com.sv.equipment.service.mapper;

import com.sv.equipment.domain.Equipment;
import com.sv.equipment.domain.Job;
import com.sv.equipment.domain.dto.EquipmentDTO;
import com.sv.equipment.domain.dto.JobDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Job} and its DTO {@link JobDTO}.
 */
@Mapper(componentModel = "spring")
public interface JobMapper extends EntityMapper<JobDTO, Job> {
    @Mapping(target = "equipment", source = "equipment", qualifiedByName = "equipmentId")
    JobDTO toDto(Job s);

    @Named("equipmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EquipmentDTO toDtoEquipmentId(Equipment equipment);
}
