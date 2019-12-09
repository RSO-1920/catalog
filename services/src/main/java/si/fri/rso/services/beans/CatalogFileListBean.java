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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class CatalogFileListBean {
    @Inject
    private EntityManager em;

    @Inject
    FileConverter fileConverter;

    public List<FileDTO> getFilesWithKeywords (String keyWords) {
        List<FileEntity> files = new ArrayList<FileEntity>();

        String[] keywords = keyWords.trim().split(",");
        // System.out.println(Arrays.toString(keywords));
        for (String key : keywords) {
            // System.out.print(key + " ");
            try {
                Query q = em.createNamedQuery("selectFilesWithKeywords").setParameter(1, "%" + key + "%");
                List<FileEntity> filesTmp = q.getResultList();

                for (FileEntity entity : filesTmp) {
                    if (!files.contains(entity)) {
                        files.add(entity);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        List<FileDTO> fileDTO = new ArrayList<FileDTO>();
        for (FileEntity fileEntity : files) {
            fileDTO.add(fileConverter.toDTO(fileEntity));
        }


        return fileDTO;
    }

    public List<FileDTO> getChannelFiles(Integer channelId) {

        List<FileEntity> channelFiles = null;
        
        try {
            Query q = em.createNamedQuery("selectFilesOnChannel").setParameter(1, channelId);
            channelFiles = q.getResultList();
            List<FileDTO> fileDTO = new ArrayList<FileDTO>();
            for (FileEntity fileEntity : channelFiles) {
                fileDTO.add(fileConverter.toDTO(fileEntity));
            }
            return fileDTO;
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
            List<FileDTO> fileDTO = new ArrayList<FileDTO>();
            for (FileEntity fileEntity : channelFiles) {
                fileDTO.add(fileConverter.toDTO(fileEntity));
            }
            return fileDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
