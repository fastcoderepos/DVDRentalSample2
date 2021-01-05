package com.fastcode.dvdrentalsample2.restcontrollers.extended;

import com.fastcode.dvdrentalsample2.application.extended.film.IFilmAppServiceExtended;
import com.fastcode.dvdrentalsample2.application.extended.language.ILanguageAppServiceExtended;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.restcontrollers.core.LanguageController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/language/extended")
public class LanguageControllerExtended extends LanguageController {

    public LanguageControllerExtended(
        ILanguageAppServiceExtended languageAppServiceExtended,
        IFilmAppServiceExtended filmAppServiceExtended,
        LoggingHelper helper,
        Environment env
    ) {
        super(languageAppServiceExtended, filmAppServiceExtended, helper, env);
    }
    //Add your custom code here

}
