package com.uni.receipesrest.repository;

import com.uni.receipesrest.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {
    Optional<Measurement> findByName(String name);
}
