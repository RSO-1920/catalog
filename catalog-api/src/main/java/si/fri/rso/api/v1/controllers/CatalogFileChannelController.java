package si.fri.rso.api.v1.controllers;

import si.fri.rso.api.v1.MainController;
import si.fri.rso.config.CatalogConfigProperties;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.services.beans.CatalogFileListBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileChannelController extends MainController {

    @Inject
    CatalogFileListBean catalogFileListBean;

    @Inject
    CatalogConfigProperties catalogConfigProperties;

    @GET
    @Path("channel/{channelId}")
    public Response getChannelFilesMetadata(@PathParam("channelId") Integer channelId){

        System.out.println("File downlaod url config: " + this.catalogConfigProperties.getDownloadFileApiUrl());

        if (channelId == null) {
            return Response.status(400).entity(this.responseError(400, "param channelId is missing")).build();
        }

        List<FileDTO> channelFiles = catalogFileListBean.getChannelFiles(channelId);

        return Response.status(200).entity(this.responseOk("", channelFiles)).build();
    }

    @GET
    @Path("user/{userId}")
    public Response getUserFilesMetadata(@PathParam("userId") Integer userId) {
        if (userId == null) {
            return Response.status(400).entity(this.responseError(400, "param userId is missing")).build();
        }

        List<FileDTO> usersFiles = catalogFileListBean.getUsersFiles(userId);

        return Response.status(200).entity(this.responseOk("", usersFiles)).build();
    }
}
