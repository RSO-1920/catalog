package si.fri.rso.models.converters;

import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.models.entities.FileEntity;
import si.fri.rso.models.entities.FileOnChannelEntity;
import si.fri.rso.models.entities.FileOwnerEntity;

public class NewFileConverter {

    public static FileEntity toFileEntity(NewFileMetadataDTO dto) {

        FileEntity entity = new FileEntity();
        entity.setFilePath(dto.getFilePath());
        entity.setFileName(dto.getFileName());
        entity.setFileType(dto.getFileType());

        return entity;
    }

    public static FileOnChannelEntity toFileOnChannelEntity(NewFileMetadataDTO dto, FileEntity fileEntity) {

        FileOnChannelEntity entity = new FileOnChannelEntity();
        entity.setChannelId(dto.getChannelId());
        entity.setFileEntity(fileEntity);

        return entity;
    }

    public static FileOwnerEntity toFileOwnerEntity(NewFileMetadataDTO dto, FileEntity fileEntity) {

        FileOwnerEntity entity = new FileOwnerEntity();
        entity.setUserId(dto.getUserId());
        entity.setFileEntity(fileEntity);
        return entity;
    }

}
