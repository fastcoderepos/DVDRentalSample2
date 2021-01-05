package com.fastcode.dvdrentalsample2.application.extended.authorization.user;

import com.fastcode.dvdrentalsample2.application.core.authorization.user.IUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapperExtended extends IUserMapper {}
