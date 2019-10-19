package si.fri.rso.api.v1.controllers;

import com.google.gson.Gson;
import si.fri.rso.api.v1.MainController;
import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.services.beans.FileBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileController extends MainController {

    @Inject
    FileBean fileBean;

    @POST
    public Response uploadFileMetadata(String body) {

        System.out.println("BODY: " + body);

        Gson gson = new Gson();

        NewFileMetadataDTO newFileMetadata = gson.fromJson(body, NewFileMetadataDTO.class);

        fileBean.createFileMetadata(newFileMetadata);

        return Response.status(200).entity(newFileMetadata).build();
    }
}
