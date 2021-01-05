package com.fastcode.dvdrentalsample2.domain.extended.city;

import com.fastcode.dvdrentalsample2.domain.core.city.ICityRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("cityRepositoryExtended")
public interface ICityRepositoryExtended extends ICityRepository {
    //Add your custom code here
}
