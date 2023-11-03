package com.sv.equipment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A Equipment.
 */
@Entity
@Table(name = "equipment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private java.lang.String name;

    @Column(name = "status")
    private String equipmentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "equipment" }, allowSetters = true)
    private Job job;

    public Long getId() {
        return this.id;
    }

    public Equipment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Equipment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getEquipmentStatus() {
        return this.equipmentStatus;
    }

    public Equipment status(String equipmentStatus) {
        this.setEquipmentStatus(equipmentStatus);
        return this;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return this.job;
    }

    public Equipment job(Job job) {
        this.setJob(job);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipment)) {
            return false;
        }
        return getId() != null && getId().equals(((Equipment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", status='" + getEquipmentStatus() + "'" +
                "}";
    }
}
