package com.fastcode.dvdrentalsample2.domain.extended.address;

import com.fastcode.dvdrentalsample2.domain.core.address.IAddressRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("addressRepositoryExtended")
public interface IAddressRepositoryExtended extends IAddressRepository {
    //Add your custom code here
}
