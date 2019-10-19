package si.fri.rso.models.converters;

import si.fri.rso.lib.FileDTO;
import si.fri.rso.models.entities.FileEntity;

public class FileConverter {

    public static FileDTO toDTO(FileEntity fileEntity) {

        FileDTO file = new FileDTO();
        file.setFileId(fileEntity.getFileId());
        file.setFileName(fileEntity.getFileName());
        file.setFilePath(fileEntity.getFilePath());
        file.setFileType(fileEntity.getFileType());

        return file;

    }
}
