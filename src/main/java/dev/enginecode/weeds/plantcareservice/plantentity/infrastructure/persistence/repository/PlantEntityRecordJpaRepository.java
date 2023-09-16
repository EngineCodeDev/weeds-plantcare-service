package dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.repository;

import dev.enginecode.weeds.plantcareservice.plantentity.infrastructure.persistence.model.PlantEntityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlantEntityRecordJpaRepository extends JpaRepository<PlantEntityRecord, UUID> {
}
