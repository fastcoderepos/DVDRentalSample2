package com.fastcode.dvdrentalsample2.domain.extended.filmactor;

import com.fastcode.dvdrentalsample2.domain.core.filmactor.IFilmActorRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("filmActorRepositoryExtended")
public interface IFilmActorRepositoryExtended extends IFilmActorRepository {
    //Add your custom code here
}
