package com.uni.receipesrest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quantities",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"quantity", "measurement_id"})})
@Entity
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double quantity;

    @ManyToOne
    private Measurement measurement;

}
