package com.fastcode.dvdrentalsample2.application.extended.language;

import com.fastcode.dvdrentalsample2.application.core.language.LanguageAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.language.ILanguageRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("languageAppServiceExtended")
public class LanguageAppServiceExtended extends LanguageAppService implements ILanguageAppServiceExtended {

    public LanguageAppServiceExtended(
        ILanguageRepositoryExtended languageRepositoryExtended,
        ILanguageMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(languageRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
