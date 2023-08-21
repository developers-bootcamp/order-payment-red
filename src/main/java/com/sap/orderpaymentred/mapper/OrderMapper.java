package com.sap.orderpaymentred.mapper;

import com.sap.orderpaymentred.dto.OrderDTO;
import com.sap.orderpaymentred.dto.PaymentDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    PaymentDetailsDto OrderDtoToPaymentDetailsDto(OrderDTO orderDTO);
}

