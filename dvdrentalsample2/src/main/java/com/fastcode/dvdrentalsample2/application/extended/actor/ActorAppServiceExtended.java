package com.fastcode.dvdrentalsample2.application.extended.actor;

import com.fastcode.dvdrentalsample2.application.core.actor.ActorAppService;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.domain.extended.actor.IActorRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("actorAppServiceExtended")
public class ActorAppServiceExtended extends ActorAppService implements IActorAppServiceExtended {

    public ActorAppServiceExtended(
        IActorRepositoryExtended actorRepositoryExtended,
        IActorMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(actorRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
