package dev.enginecode.weeds.plantcareservice.query.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlantJpaRepository extends JpaRepository<PlantEntity, UUID> {

}
