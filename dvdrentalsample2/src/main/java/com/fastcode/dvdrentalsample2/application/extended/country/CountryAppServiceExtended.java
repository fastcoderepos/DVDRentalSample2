package com.fastcode.dvdrentalsample2.application.extended.country;

import com.fastcode.dvdrentalsample2.application.core.country.CountryAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.country.ICountryRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("countryAppServiceExtended")
public class CountryAppServiceExtended extends CountryAppService implements ICountryAppServiceExtended {

    public CountryAppServiceExtended(
        ICountryRepositoryExtended countryRepositoryExtended,
        ICountryMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(countryRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
