package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "ps_plant_entity_test")
public class PlantEntityRecord {
    @Id
    private UUID id;
    private String name;


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(final UUID id) {
        this.id = id;
    }

    void setName(final String name) {
        this.name = name;
    }

}
