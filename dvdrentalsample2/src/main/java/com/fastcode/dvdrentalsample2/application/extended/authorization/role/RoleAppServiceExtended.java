package com.fastcode.dvdrentalsample2.application.extended.authorization.role;

import com.fastcode.dvdrentalsample2.application.core.authorization.role.RoleAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.authorization.role.IRoleRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("roleAppServiceExtended")
public class RoleAppServiceExtended extends RoleAppService implements IRoleAppServiceExtended {

    public RoleAppServiceExtended(
        IRoleRepositoryExtended roleRepositoryExtended,
        IRoleMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(roleRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
