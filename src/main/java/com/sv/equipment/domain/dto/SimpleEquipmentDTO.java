package com.sv.equipment.domain.dto;

import com.sv.equipment.domain.enumeration.EquipmentStatus;

public record SimpleEquipmentDTO(Long id, String name, EquipmentStatus equipmentStatus) {
}
