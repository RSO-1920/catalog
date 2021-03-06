package si.fri.rso.models.entities;

import si.fri.rso.models.interfaces.MainEntity;

import javax.persistence.*;

@Entity
@Table(name = "file_keywords")

@NamedNativeQueries({
        @NamedNativeQuery(name = "findkeywords", query = "SELECT * FROM file_keywords WHERE file_keywords.fk_file_id = ?1", resultClass = FileKeywordsEntity.class),
        @NamedNativeQuery(name = "deleteFileKeywords",
                query = "DELETE FROM file_keywords WHERE file_keywords.fk_file_id = ?1"),
})

public class FileKeywordsEntity implements MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_file_id")
    private FileEntity fileEntity;

    @Column(name = "keyword")
    private String keyword;

    public Integer getId() {
        return this.id;
    }

    public FileEntity getFileEntity() {
        return this.fileEntity;
    }
    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
