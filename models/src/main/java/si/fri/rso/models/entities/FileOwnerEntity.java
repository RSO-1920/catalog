package si.fri.rso.models.entities;

import si.fri.rso.models.interfaces.MainEntity;

import javax.persistence.*;

@Entity
@Table(name = "file_owner")

@NamedNativeQueries({
        @NamedNativeQuery(name = "deleteFilesOnUser",
                query = "DELETE FROM file_owner WHERE file_owner.fk_file_id = ?1"),
})


public class FileOwnerEntity implements MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_file_id")
    private FileEntity fileEntity;

    @Column(name = "user_id")
    private String userId;

    public Integer getId() {
        return this.id;
    }

    public FileEntity getFileEntity() {
        return this.fileEntity;
    }
    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
