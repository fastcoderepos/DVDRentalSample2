package com.fastcode.dvdrentalsample2.application.extended.store;

import com.fastcode.dvdrentalsample2.application.core.store.StoreAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.address.IAddressRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.staff.IStaffRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.store.IStoreRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("storeAppServiceExtended")
public class StoreAppServiceExtended extends StoreAppService implements IStoreAppServiceExtended {

    public StoreAppServiceExtended(
        IStoreRepositoryExtended storeRepositoryExtended,
        IAddressRepositoryExtended addressRepositoryExtended,
        IStaffRepositoryExtended staffRepositoryExtended,
        IStoreMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(storeRepositoryExtended, addressRepositoryExtended, staffRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
