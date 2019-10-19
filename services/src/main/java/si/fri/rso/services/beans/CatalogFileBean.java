package si.fri.rso.services.beans;

import si.fri.rso.lib.FileDTO;
import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.models.converters.FileConverter;
import si.fri.rso.models.converters.NewFileConverter;
import si.fri.rso.models.entities.FileEntity;
import si.fri.rso.models.entities.FileOnChannelEntity;
import si.fri.rso.models.entities.FileOwnerEntity;
import si.fri.rso.models.entities.MainEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.persistence.EntityManager;

@ApplicationScoped
public class CatalogFileBean {
    @Inject
    private EntityManager em;


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

    private MainEntity createNewEntity(MainEntity entity) {
        try {
            beginTx();
            em.persist(entity);
            commitTx();
        } catch (Exception e) {
            System.out.println("Rollback transaction file on channel");
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
