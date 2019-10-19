package si.fri.rso.api.v1.controllers;

import com.google.gson.Gson;
import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.services.beans.FileBean;
import si.fri.rso.services.beans.FileOnChannelBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileController {

    @Inject
    FileBean fileBean;

    @Inject
    FileOnChannelBean fileOnChannelBean;

    @GET
    @Path("/channel/{channelId}")
    public Response getChannelFilesMetadata(@PathParam("channelId") Integer channelId){
        System.out.println("Channel id: " + channelId);

        fileOnChannelBean.getChannelFiles(channelId);

        return Response.status(200).entity("ok").build();
    }

    @POST
    public Response uploadFileMetadata(String body) {

        System.out.println("BODY: " + body);

        Gson gson = new Gson();

        NewFileMetadataDTO newFileMetadata = gson.fromJson(body, NewFileMetadataDTO.class);

        fileBean.createFileMetadata(newFileMetadata);

        return Response.status(200).entity(newFileMetadata).build();
    }
}
