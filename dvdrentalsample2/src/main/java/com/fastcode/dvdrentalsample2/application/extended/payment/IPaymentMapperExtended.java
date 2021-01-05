package com.fastcode.dvdrentalsample2.application.extended.payment;

import com.fastcode.dvdrentalsample2.application.core.payment.IPaymentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPaymentMapperExtended extends IPaymentMapper {}
