package dev.enginecode.weeds.plantcareservice.query.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ps_plant_entity")
public class PlantEntity {
    @Id
    private UUID id;
    private String name;


}
