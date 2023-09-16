package dev.enginecode.weeds.plantcareservice.plantentity.presentation.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ps_plant_entity")
public class PlantEntity {
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
