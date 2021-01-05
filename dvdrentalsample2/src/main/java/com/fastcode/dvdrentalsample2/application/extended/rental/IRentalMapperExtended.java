package com.fastcode.dvdrentalsample2.application.extended.rental;

import com.fastcode.dvdrentalsample2.application.core.rental.IRentalMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRentalMapperExtended extends IRentalMapper {}
