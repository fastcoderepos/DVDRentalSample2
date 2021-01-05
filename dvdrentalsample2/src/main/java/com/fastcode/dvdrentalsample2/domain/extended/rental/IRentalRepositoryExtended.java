package com.fastcode.dvdrentalsample2.domain.extended.rental;

import com.fastcode.dvdrentalsample2.domain.core.rental.IRentalRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("rentalRepositoryExtended")
public interface IRentalRepositoryExtended extends IRentalRepository {
    //Add your custom code here
}
