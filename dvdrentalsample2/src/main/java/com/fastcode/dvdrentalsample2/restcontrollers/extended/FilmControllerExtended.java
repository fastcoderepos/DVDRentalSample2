package com.fastcode.dvdrentalsample2.restcontrollers.extended;

import com.fastcode.dvdrentalsample2.application.extended.film.IFilmAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.filmactor.IFilmActorAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.filmcategory.IFilmCategoryAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.inventory.IInventoryAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.language.ILanguageAppServiceExtended;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.restcontrollers.core.FilmController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/film/extended")
public class FilmControllerExtended extends FilmController {

    public FilmControllerExtended(
        IFilmAppServiceExtended filmAppServiceExtended,
        IFilmActorAppServiceExtended filmActorAppServiceExtended,
        IFilmCategoryAppServiceExtended filmCategoryAppServiceExtended,
        IInventoryAppServiceExtended inventoryAppServiceExtended,
        ILanguageAppServiceExtended languageAppServiceExtended,
        LoggingHelper helper,
        Environment env
    ) {
        super(
            filmAppServiceExtended,
            filmActorAppServiceExtended,
            filmCategoryAppServiceExtended,
            inventoryAppServiceExtended,
            languageAppServiceExtended,
            helper,
            env
        );
    }
    //Add your custom code here

}
