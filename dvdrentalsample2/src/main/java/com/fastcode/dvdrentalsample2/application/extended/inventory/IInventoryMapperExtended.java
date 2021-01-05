package com.fastcode.dvdrentalsample2.application.extended.inventory;

import com.fastcode.dvdrentalsample2.application.core.inventory.IInventoryMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IInventoryMapperExtended extends IInventoryMapper {}
