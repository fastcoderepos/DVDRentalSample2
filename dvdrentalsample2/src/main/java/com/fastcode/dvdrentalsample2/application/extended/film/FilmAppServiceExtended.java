package com.fastcode.dvdrentalsample2.application.extended.film;

import com.fastcode.dvdrentalsample2.application.core.film.FilmAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.film.IFilmRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.language.ILanguageRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("filmAppServiceExtended")
public class FilmAppServiceExtended extends FilmAppService implements IFilmAppServiceExtended {

    public FilmAppServiceExtended(
        IFilmRepositoryExtended filmRepositoryExtended,
        ILanguageRepositoryExtended languageRepositoryExtended,
        IFilmMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(filmRepositoryExtended, languageRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
