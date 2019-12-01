package si.fri.rso.services.beans;

import si.fri.rso.lib.FileDTO;
import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.models.converters.FileConverter;
import si.fri.rso.models.converters.NewFileConverter;
import si.fri.rso.models.entities.FileEntity;
import si.fri.rso.models.entities.FileOnChannelEntity;
import si.fri.rso.models.entities.FileOwnerEntity;
import si.fri.rso.models.interfaces.MainEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequestScoped
public class CatalogFileBean {
    @Inject
    private EntityManager em;

    public FileDTO getFile(Integer fileId) {
        FileEntity file = em.find(FileEntity.class, fileId);

        if (file == null) {
            return null;
        }

        return FileConverter.toDTO(file);
    }


    public FileDTO createFileMetadata(NewFileMetadataDTO newFile) {
        FileEntity fileEntity = NewFileConverter.toFileEntity(newFile);

        fileEntity = (FileEntity) this.createNewEntity(fileEntity);

        if (fileEntity == null || fileEntity.getFileId() == null) {
            return null;
        }

        /**
         * When file metadata is created write it into connected tables file owner and file channel.
         * */
        FileOnChannelEntity fileOnChannelEntity = NewFileConverter.toFileOnChannelEntity(newFile, fileEntity);
        fileOnChannelEntity = (FileOnChannelEntity) this.createNewEntity(fileOnChannelEntity);

        if (fileOnChannelEntity == null || fileOnChannelEntity.getId() == null) {
            return null;
        }

        FileOwnerEntity fileOwnerEntity = NewFileConverter.toFileOwnerEntity(newFile, fileEntity);
        fileOwnerEntity = (FileOwnerEntity) this.createNewEntity(fileOwnerEntity);

        if (fileOwnerEntity == null || fileOwnerEntity.getId() == null) {
            return null;
        }

        return FileConverter.toDTO(fileEntity);
    }

    public boolean deleteFile(Integer fileId) {

        Query query = em.createNamedQuery("deleteFilesOnUser").setParameter(1, fileId);
        Query query1 = em.createNamedQuery("deleteFilesOnChannel").setParameter(1, fileId);
        Query query2 = em.createNamedQuery("deleteFile").setParameter(1, fileId);
        try {
            beginTx();
            query.executeUpdate();
            query1.executeUpdate();
            query2.executeUpdate();
            commitTx();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTx();
            return false;
        }

        return true;
    }

    private MainEntity createNewEntity(MainEntity entity) {
        try {
            beginTx();
            em.persist(entity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            return null;
        }
        return  entity;
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
