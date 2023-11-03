package com.sv.equipment.domain.dto;

import com.sv.equipment.domain.enumeration.EquipmentStatus;
import lombok.Builder;

@Builder
public record EquipmentDTO(Long id, String name, EquipmentStatus equipmentStatus) {
}
