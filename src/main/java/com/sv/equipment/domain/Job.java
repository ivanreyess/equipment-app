package com.sv.equipment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Job.
 */
@Entity
@Table(name = "job")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job")
    @JsonIgnoreProperties(value = { "job" }, allowSetters = true)
    private Set<Equipment> equipment = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Job id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Job name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Equipment> getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Set<Equipment> equipment) {
        if (this.equipment != null) {
            this.equipment.forEach(i -> i.setJob(null));
        }
        if (equipment != null) {
            equipment.forEach(i -> i.setJob(this));
        }
        this.equipment = equipment;
    }

    public Job equipment(Set<Equipment> equipment) {
        this.setEquipment(equipment);
        return this;
    }

    public Job addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
        equipment.setJob(this);
        return this;
    }

    public Job removeEquipment(Equipment equipment) {
        this.equipment.remove(equipment);
        equipment.setJob(null);
        return this;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        return getId() != null && getId().equals(((Job) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Job{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
