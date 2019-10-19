package si.fri.rso.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "file_owner")
public class FileOwnerEntity implements MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_file_id")
    private FileEntity fileEntity;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() {
        return this.id;
    }

    public FileEntity getFileEntity() {
        return this.fileEntity;
    }
    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
