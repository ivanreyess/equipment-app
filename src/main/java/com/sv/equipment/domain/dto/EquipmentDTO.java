package com.sv.equipment.domain.dto;

import com.sv.equipment.domain.enumeration.EquipmentStatus;

public record EquipmentDTO(long id, String name, EquipmentStatus equipmentStatus) {
}
