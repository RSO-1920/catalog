package si.fri.rso.api.v1;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogFileController {
    @POST
    public Response uploadFileMetadata() {

        return Response.status(200).entity("Upload success").build();
    }
}
