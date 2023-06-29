package com.yanpgabriel.duck.modules.user.role;

import com.yanpgabriel.duck.modules.user.profile.ProfileService;
import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.util.DuckUtil;
import com.yanpgabriel.duck.util.config.DuckRoles;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/role")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Inject
    RoleService service;

    @GET
    @RolesAllowed({DuckRoles.DUCK_ADM, DuckRoles.DUCK_USER_LIST})
    public Response list(@QueryParam("sort") String sortQuery,
                         @QueryParam("page") @DefaultValue("0") int pageIndex,
                         @QueryParam("size") @DefaultValue("10") int pageSize) {
        Page page = Page.of(pageIndex, pageSize);
        Sort sort = DuckUtil.makeSort(sortQuery);
        return BaseResponse.instaceSuccess().entity(service.list(page, sort)).toResponse();
    }
}
