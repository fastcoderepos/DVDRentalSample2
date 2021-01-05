package com.fastcode.dvdrentalsample2.domain.extended.filmcategory;

import com.fastcode.dvdrentalsample2.domain.core.filmcategory.IFilmCategoryRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("filmCategoryRepositoryExtended")
public interface IFilmCategoryRepositoryExtended extends IFilmCategoryRepository {
    //Add your custom code here
}
