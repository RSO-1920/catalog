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
public class FileOnChannelBean {
    @Inject
    private EntityManager em;


    public List<FileDTO> getChannelFiles(Integer channelId) {
        Query q = em.createNamedQuery("selectFilesOnChannel").setParameter(1, channelId);
        List<FileEntity> channelFiles =  q.getResultList();

        return (List<FileDTO>) channelFiles.stream().map(FileConverter::toEntity).collect(Collectors.toList());
    }
}
