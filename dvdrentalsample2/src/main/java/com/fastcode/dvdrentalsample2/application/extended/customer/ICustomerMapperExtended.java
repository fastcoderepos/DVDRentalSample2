package com.fastcode.dvdrentalsample2.application.extended.customer;

import com.fastcode.dvdrentalsample2.application.core.customer.ICustomerMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapperExtended extends ICustomerMapper {}
