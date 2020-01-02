package si.fri.rso.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService(value = "rso1920-catalog")
@ApplicationPath("/v1")
@OpenAPIDefinition(info = @Info(title = "Catalog REST API", version = "v1", contact = @Contact(), license = @License(),
        description = "Catalog file metadata manager"), servers = @Server(url ="http://localhost:8088/v1"))
public class CatalogApi extends Application {
}
