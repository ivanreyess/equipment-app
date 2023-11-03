package com.sv.equipment.service.mapper;

import com.sv.equipment.domain.Equipment;
import com.sv.equipment.domain.dto.EquipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Equipment} and its DTO {@link EquipmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper extends EntityMapper<EquipmentDTO, Equipment> {
}
