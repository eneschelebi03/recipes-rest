package com.uni.receipesrest.mapper;

import com.uni.receipesrest.model.Quantity;
import com.uni.receipesrest.model.dto.QuantityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuantityMapper {

    QuantityDto quantityToQuantityDto(Quantity quantity);
}
