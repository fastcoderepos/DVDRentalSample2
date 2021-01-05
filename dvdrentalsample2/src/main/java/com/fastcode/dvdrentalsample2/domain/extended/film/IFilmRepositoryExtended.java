package com.fastcode.dvdrentalsample2.domain.extended.film;

import com.fastcode.dvdrentalsample2.domain.core.film.IFilmRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("filmRepositoryExtended")
public interface IFilmRepositoryExtended extends IFilmRepository {
    //Add your custom code here
}
