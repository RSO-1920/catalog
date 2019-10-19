package si.fri.rso.api.v1.controllers;

import com.google.gson.Gson;
import si.fri.rso.api.v1.MainController;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.services.beans.CatalogFileBean;

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
    CatalogFileBean catalogFileBean;

    @POST
    public Response uploadFileMetadata(String body) {
        Gson gson = new Gson();
        NewFileMetadataDTO newFileMetadata = gson.fromJson(body, NewFileMetadataDTO.class);

        if (newFileMetadata.getChannelId() == null || newFileMetadata.getFileName() == null ||
                newFileMetadata.getFilePath() == null || newFileMetadata.getFileType() == null ||newFileMetadata.getUserId() == null) {
            return Response.status(400).entity(this.responseError(400, "channelId, fileName, filePath, fileType or userId is missing")).build();
        }

        FileDTO newFile = catalogFileBean.createFileMetadata(newFileMetadata);
        if (newFile == null) {
            return Response.status(500).entity(this.responseError(500, "error when writing file metadata to DB")).build();
        }

        return Response.status(200).entity(this.responseOk("", newFile)).build();
    }
}
