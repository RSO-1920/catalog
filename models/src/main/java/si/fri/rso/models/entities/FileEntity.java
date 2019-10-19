package si.fri.rso.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "file")

@NamedNativeQueries({
        @NamedNativeQuery(name = "selectFilesOnChannel",
                query = "SELECT * FROM file INNER JOIN file_on_channel ON file.file_id = file_on_channel.fk_file_id WHERE file_on_channel.channel_id = ?1",
                resultClass = FileEntity.class)
})

public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name =  "file_type")
    private String fileType;

    public Integer getFileId() {
        return this.fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return this.fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
