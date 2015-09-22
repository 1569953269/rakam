package org.rakam;

import javax.inject.Inject;
import org.rakam.server.http.HttpService;
import org.rakam.server.http.annotations.Api;
import org.rakam.server.http.annotations.ApiOperation;
import org.rakam.server.http.annotations.Authorization;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by buremba <Burak Emre Kabakcı> on 18/05/15 22:02.
 */
@Path("/admin")
@Api(value = "/admin", description = "System operations", tags = "admin")
public class AdminHttpService extends HttpService {
    private final SystemRegistry systemRegistry;

    @Inject
    public AdminHttpService(SystemRegistry systemRegistry) {
        this.systemRegistry = systemRegistry;
    }


    @ApiOperation(value = "List installed modules",
            authorizations = @Authorization(value = "api_key", type = "api_key")
    )
    @GET
    @Path("/modules")
    public List<SystemRegistry.Module> getModules() {
        return systemRegistry.getModules();
    }
}
