package si.fri.rso.models.converters;

import si.fri.rso.lib.NewFileMetadataDTO;
import si.fri.rso.models.entities.FileEntity;

public class NewFileConverter {

    public static FileEntity toEntity(NewFileMetadataDTO dto) {

        FileEntity entity = new FileEntity();
        entity.setFileName(dto.getFileName());

        return entity;

    }
}
