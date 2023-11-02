package com.sv.equipment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sv.equipment.domain.enumeration.EquipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EquipmentStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
    @JsonIgnoreProperties(value = { "equipment" }, allowSetters = true)
    private Set<Job> jobs = new HashSet<>();

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

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentStatus getStatus() {
        return this.status;
    }

    public Equipment status(EquipmentStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        if (this.jobs != null) {
            this.jobs.forEach(i -> i.setEquipment(null));
        }
        if (jobs != null) {
            jobs.forEach(i -> i.setEquipment(this));
        }
        this.jobs = jobs;
    }

    public Equipment jobs(Set<Job> jobs) {
        this.setJobs(jobs);
        return this;
    }

    public Equipment addJob(Job job) {
        this.jobs.add(job);
        job.setEquipment(this);
        return this;
    }

    public Equipment removeJob(Job job) {
        this.jobs.remove(job);
        job.setEquipment(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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

    // prettier-ignore
    @Override
    public String toString() {
        return "Equipment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
