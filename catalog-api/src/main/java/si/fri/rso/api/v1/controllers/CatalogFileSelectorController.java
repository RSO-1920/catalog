package si.fri.rso.api.v1.controllers;

import com.kumuluz.ee.logs.cdi.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.api.v1.MainController;
import si.fri.rso.config.CatalogConfigProperties;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.lib.responses.ResponseDTO;
import si.fri.rso.services.beans.CatalogFileListBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@ApplicationScoped
@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileSelectorController extends MainController {

    @Inject
    CatalogFileListBean catalogFileListBean;

    @Inject
    CatalogConfigProperties catalogConfigProperties;

    @Context
    ContainerRequestContext reqContext;

    @GET
    @Operation(description = "Get files on channel metadata", summary = "files on channel", tags = "file, channel", responses = {
            @ApiResponse(responseCode = "200",
                    description = "getting files on channel metadata success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileDTO.class)))
            ),
            @ApiResponse(responseCode = "400",
                    description = "params missing",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))
            ),
    })
    @Timed(name = "catalog_channel_time_channelId")
    @Counted(name = "catalog_channel_counted_channelId")
    @Metered(name = "catalog_channel_metered_channelId")
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
    @Operation(description = "Get files user's files", summary = "files on user", tags = "file, user", responses = {
            @ApiResponse(responseCode = "200",
                    description = "getting files on user metadata success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileDTO.class)))
            ),
            @ApiResponse(responseCode = "400",
                    description = "params missing",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))
            ),
    })
    @Timed(name = "catalog_channel_time_userId")
    @Counted(name = "catalog_channel_counted_userId")
    @Metered(name = "catalog_channel_metered_userId")
    @Path("user/{userId}")
    public Response getUserFilesMetadata(@PathParam("userId") Integer userId) {
        if (userId == null) {
            return Response.status(400).entity(this.responseError(400, "param userId is missing")).build();
        }

        List<FileDTO> usersFiles = catalogFileListBean.getUsersFiles(userId);

        return Response.status(200).entity(this.responseOk("", usersFiles)).build();
    }

    @GET
    @Operation(description = "Search files", summary = "files keywords", tags = "file, keywords", responses = {
            @ApiResponse(responseCode = "200",
                    description = "getting files based on keywords success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileDTO.class)))
            ),
            @ApiResponse(responseCode = "400",
                    description = "params missing.. keywords",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))
            ),
    })
    @Path("keywords")
    public Response getFilesBaseOnKeywords(@QueryParam("key") String keywords) {
        if (keywords == null) {
            return Response.status(400).entity(this.responseError(400, "query params missing")).build();
        }
        List<FileDTO> usersFiles = catalogFileListBean.getFilesWithKeywords(keywords);
        return Response.status(200).entity(this.responseOk("", usersFiles)).build();
    }
}
