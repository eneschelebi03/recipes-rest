package com.uni.receipesrest.service;

import com.uni.receipesrest.model.Measurement;
import com.uni.receipesrest.model.Quantity;
import com.uni.receipesrest.repository.QuantityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuantityService {

    private final QuantityRepository quantityRepository;
    private final MeasurementService measurementService;

    public QuantityService(QuantityRepository quantityRepository, MeasurementService measurementService) {
        this.quantityRepository = quantityRepository;
        this.measurementService = measurementService;
    }


    public Quantity saveQuantity(Quantity quantity) {

        Measurement measurement = this.measurementService.saveMeasurement(quantity.getMeasurement());
        quantity.setMeasurement(measurement);

        Optional<Quantity> quantityOpt = this.quantityRepository
                .findByQuantityAndMeasurement(quantity.getQuantity(), quantity.getMeasurement());


        return quantityOpt.orElseGet(() -> this.quantityRepository.save(quantity));
    }
}
