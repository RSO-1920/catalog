package si.fri.rso.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "file_owner")
public class FileOwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_file_id")
    private FileEntity fileEntity;

    @Column(name = "user_id")
    private Integer userId;

    public FileEntity getFileEntity() {
        return this.fileEntity;
    }
    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setChannelId(Integer userId) {
        this.userId = userId;
    }

}
