package si.fri.rso.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name") // imena stolpcev v bazi
    private String fileName;

    public Integer getFileId() {
        return this.fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.getFileName();
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

}
