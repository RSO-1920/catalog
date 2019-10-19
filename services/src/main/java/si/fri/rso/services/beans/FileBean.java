package si.fri.rso.services.beans;

import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.models.converters.NewFileConverter;
import si.fri.rso.models.entities.FileEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.persistence.EntityManager;

@ApplicationScoped
public class FileBean {
    @Inject
    private EntityManager em;


    public void createFileMetadata(NewFileMetadataDTO newFile) {

        FileEntity fileEntity = NewFileConverter.toEntity(newFile);
        try {
            beginTx();
            em.persist(fileEntity);
            System.out.println("file creation");
            commitTx();
        } catch (Exception e) {
            System.out.println("Rollback transaction");
            rollbackTx();
        }

        if (fileEntity.getFileId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }


    }



    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
