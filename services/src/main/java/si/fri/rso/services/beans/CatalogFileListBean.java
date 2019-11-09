package si.fri.rso.services.beans;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.models.converters.FileConverter;
import si.fri.rso.models.entities.FileEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class CatalogFileListBean {
    @Inject
    private EntityManager em;

    @Inject
    @DiscoverService(value = "rso1920-catalog")
    private Optional<URL> url;

    public List<FileDTO> getChannelFiles(Integer channelId) {
        Query q = em.createNamedQuery("selectFilesOnChannel").setParameter(1, channelId);
        List<FileEntity> channelFiles =  q.getResultList();

        System.out.println("URL: " + url);

        return (List<FileDTO>) channelFiles.stream().map(FileConverter::toDTO).collect(Collectors.toList());
    }

    public List<FileDTO> getUsersFiles(Integer userId) {
        Query q = em.createNamedQuery("selectFilesOnUser").setParameter(1, userId);
        List<FileEntity> channelFiles =  q.getResultList();

        return (List<FileDTO>) channelFiles.stream().map(FileConverter::toDTO).collect(Collectors.toList());
    }

}
