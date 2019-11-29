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

    public List<FileDTO> getChannelFiles(Integer channelId) {

        List<FileEntity> channelFiles = null;
        
        try {
            Query q = em.createNamedQuery("selectFilesOnChannel").setParameter(1, channelId);
            channelFiles = q.getResultList();
            return (List<FileDTO>) channelFiles.stream().map(FileConverter::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FileDTO> getUsersFiles(Integer userId) {

        List<FileEntity> channelFiles =  null;
        try {
            Query q = em.createNamedQuery("selectFilesOnUser").setParameter(1, userId);
            channelFiles = q.getResultList();
            return (List<FileDTO>) channelFiles.stream().map(FileConverter::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
