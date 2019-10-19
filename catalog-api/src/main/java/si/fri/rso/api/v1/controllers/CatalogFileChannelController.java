package si.fri.rso.api.v1.controllers;

import si.fri.rso.api.v1.MainController;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.services.beans.CatalogFileOnChannelBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/file/channel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileChannelController extends MainController {

    @Inject
    CatalogFileOnChannelBean catalogFileOnChannelBean;

    @GET
    @Path("{channelId}")
    public Response getChannelFilesMetadata(@PathParam("channelId") Integer channelId){

        if (channelId == null) {
            return Response.status(400).entity(this.responseError(400, "param channelId is missing")).build();
        }

        List<FileDTO> channelFiles = catalogFileOnChannelBean.getChannelFiles(channelId);

        return Response.status(200).entity(this.responseOk("", channelFiles)).build();
    }
}
