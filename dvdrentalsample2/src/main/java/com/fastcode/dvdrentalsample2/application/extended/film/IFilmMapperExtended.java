package com.fastcode.dvdrentalsample2.application.extended.film;

import com.fastcode.dvdrentalsample2.application.core.film.IFilmMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFilmMapperExtended extends IFilmMapper {}
