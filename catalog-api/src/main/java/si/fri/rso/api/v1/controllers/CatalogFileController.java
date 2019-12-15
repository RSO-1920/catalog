package si.fri.rso.api.v1.controllers;

import com.google.gson.Gson;
import com.kumuluz.ee.logs.cdi.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.api.v1.MainController;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.lib.responses.ResponseDTO;
import si.fri.rso.services.beans.CatalogFileBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Log
@ApplicationScoped
@Path("/fileMetadata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileController extends MainController {

    @Inject
    CatalogFileBean catalogFileBean;

    @Context
    ContainerRequestContext reqContext;

    @GET
    @Operation(description = "Get file metadata based on id", summary = "filemetadata id", tags = "file", responses = {
            @ApiResponse(responseCode = "200",
                    description = "get file success",
                    content = @Content(schema = @Schema(implementation = FileDTO.class))
            ),
            @ApiResponse(responseCode = "400",
                    description = "error",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))
            ),
    })
    @Path("{fileId}")
    public Response getFileMetadataBasesOnId(@PathParam("fileId") Integer fileId) {
        if (fileId == null) {
            return Response.status(400).entity(this.responseError(400, "fileId is missing")).build();
        }

        FileDTO file = this.catalogFileBean.getFile(fileId);

        if (file == null) {
            return Response.status(400).entity(this.responseError(400, "file not found")).build();
        }

        return Response.status(200).entity(this.responseOk("", file)).build();
    }

    @POST
    @Operation(description = "Save new file metadata", summary = "file metadata", tags = "file, metadata", responses = {
            @ApiResponse(responseCode = "200",
                    description = "upload file success",
                    content = @Content(schema = @Schema(implementation = FileDTO.class))
            ),
            @ApiResponse(responseCode = "400",
                    description = "missing params",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "error saving metadata",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))
            ),
    })
    @Timed(name = "catalog_file_time_upload")
    @Counted(name = "catalog_file_counted_upload")
    @Metered(name = "catalog_file_metered_upload")
    public Response uploadFileMetadata(String body) {
        Gson gson = new Gson();
        NewFileMetadataDTO newFileMetadata = gson.fromJson(body, NewFileMetadataDTO.class);

        if (newFileMetadata.getFileLabels() != null) {
            System.out.println(Arrays.toString(newFileMetadata.getFileLabels().toArray()));
        }

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

    @DELETE
    @Operation(description = "Delete file metadata", summary = "delete file metadata", tags = "file, delete", responses = {
            @ApiResponse(responseCode = "200",
                    description = "delete file metadata succes",
                    content = @Content(schema = @Schema(implementation = Boolean.class))
            ),
    })
    @Timed(name = "catalog_file_time_delete")
    @Counted(name = "catalog_file_counted_delete")
    @Metered(name = "catalog_file_metered_delete")
    @Path("{fileId}")
    public Response deleteFileMetadata(@PathParam("fileId") Integer fileId){

        System.out.println("fileId: " + fileId);
        boolean isDeleted = catalogFileBean.deleteFile(fileId);

        return Response.status(200).entity(this.responseOk("delete statement success", isDeleted)).build();
    }
}
