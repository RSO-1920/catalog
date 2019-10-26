package si.fri.rso.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-config")
public class CatalogConfigProperties {

    @ConfigValue(value = "download-file-api-url", watch = true)
    private String downloadFileApiUrl;

    public String getDownloadFileApiUrl() {
        return this.downloadFileApiUrl;
    }

    public void setDownloadFileApiUrl (String downloadFileApiUrl) {
        this.downloadFileApiUrl = downloadFileApiUrl;
    }
}
