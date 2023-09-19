package com.uni.receipesrest.service;

import com.uni.receipesrest.model.Measurement;
import com.uni.receipesrest.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Measurement saveMeasurement(Measurement measurement) {

        if (measurement == null) {
            return null;
        }

        Optional<Measurement> measurementOpt = this.measurementRepository.findByName(measurement.getName());

        return measurementOpt.orElseGet(() -> this.measurementRepository.save(measurement));
    }
}
