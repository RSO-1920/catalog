package si.fri.rso.models.converters;

import jdk.internal.loader.BuiltinClassLoader;
import si.fri.rso.lib.FileDTO;
import si.fri.rso.models.entities.FileEntity;
import si.fri.rso.models.entities.FileKeywordsEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class FileConverter {
    @Inject
    private EntityManager em;

    public FileDTO toDTO(FileEntity fileEntity) {

        FileDTO file = new FileDTO();
        file.setFileId(fileEntity.getFileId());
        file.setFileName(fileEntity.getFileName());
        file.setFilePath(fileEntity.getFilePath());
        file.setFileType(fileEntity.getFileType());


        Query q = em.createNamedQuery("findkeywords").setParameter(1, fileEntity.getFileId());
        List<FileKeywordsEntity>  filekeywords = q.getResultList();

        ArrayList<String> keywords = new ArrayList<String>();
        for (FileKeywordsEntity key : filekeywords) {
            keywords.add(key.getKeyword());
        }
        // System.out.println(Arrays.toString(keywords.toArray()));
        file.setKeywords(keywords);
        return file;

    }
}
