package com.fastcode.dvdrentalsample2.application.extended.country;

import com.fastcode.dvdrentalsample2.application.core.country.ICountryMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICountryMapperExtended extends ICountryMapper {}
