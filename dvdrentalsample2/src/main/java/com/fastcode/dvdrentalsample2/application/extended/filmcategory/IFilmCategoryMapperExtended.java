package com.fastcode.dvdrentalsample2.application.extended.filmcategory;

import com.fastcode.dvdrentalsample2.application.core.filmcategory.IFilmCategoryMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFilmCategoryMapperExtended extends IFilmCategoryMapper {}
