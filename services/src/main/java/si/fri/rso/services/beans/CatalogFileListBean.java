package si.fri.rso.services.beans;

import si.fri.rso.lib.FileDTO;
import si.fri.rso.models.converters.FileConverter;
import si.fri.rso.models.entities.FileEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatalogFileListBean {
    @Inject
    private EntityManager em;

    public List<FileDTO> getChannelFiles(Integer channelId) {
        Query q = em.createNamedQuery("selectFilesOnChannel").setParameter(1, channelId);
        List<FileEntity> channelFiles =  q.getResultList();

        return (List<FileDTO>) channelFiles.stream().map(FileConverter::toDTO).collect(Collectors.toList());
    }

    public List<FileDTO> getUsersFiles(Integer userId) {
        Query q = em.createNamedQuery("selectFilesOnUser").setParameter(1, userId);
        List<FileEntity> channelFiles =  q.getResultList();

        return (List<FileDTO>) channelFiles.stream().map(FileConverter::toDTO).collect(Collectors.toList());
    }

}
