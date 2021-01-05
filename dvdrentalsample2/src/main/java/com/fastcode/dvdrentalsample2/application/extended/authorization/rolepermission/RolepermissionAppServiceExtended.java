package com.fastcode.dvdrentalsample2.application.extended.authorization.rolepermission;

import com.fastcode.dvdrentalsample2.application.core.authorization.rolepermission.RolepermissionAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.authorization.permission.IPermissionRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.authorization.role.IRoleRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.authorization.rolepermission.IRolepermissionRepositoryExtended;
import com.fastcode.dvdrentalsample2.domain.extended.authorization.userrole.IUserroleRepositoryExtended;
import com.fastcode.dvdrentalsample2.security.JWTAppService;
import org.springframework.stereotype.Service;

@Service("rolepermissionAppServiceExtended")
public class RolepermissionAppServiceExtended
    extends RolepermissionAppService
    implements IRolepermissionAppServiceExtended {

    public RolepermissionAppServiceExtended(
        JWTAppService jwtAppService,
        IUserroleRepositoryExtended userroleRepositoryExtended,
        IRolepermissionRepositoryExtended rolepermissionRepositoryExtended,
        IPermissionRepositoryExtended permissionRepositoryExtended,
        IRoleRepositoryExtended roleRepositoryExtended,
        IRolepermissionMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(
            jwtAppService,
            userroleRepositoryExtended,
            rolepermissionRepositoryExtended,
            permissionRepositoryExtended,
            roleRepositoryExtended,
            mapper,
            logHelper
        );
    }
    //Add your custom code here

}
