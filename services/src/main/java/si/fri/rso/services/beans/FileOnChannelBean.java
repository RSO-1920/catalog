package si.fri.rso.services.beans;

import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.models.converters.NewFileConverter;
import si.fri.rso.models.entities.FileEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class FileOnChannelBean {
    @Inject
    private EntityManager em;


    public void getChannelFiles(Integer channelId) {
        Query q = em.createNativeQuery("SELECT * FROM file INNER JOIN file_on_channel ON file.file_id = file_on_channel.fk_file_id WHERE file_on_channel.channel_id = 1");
        List<Object[]> channelFiles = q.getResultList();

        for (Object[] ob : channelFiles) {
            System.out.println("id: " + ob[0] + " fileName: " + ob[1]);
        }

    }
}
