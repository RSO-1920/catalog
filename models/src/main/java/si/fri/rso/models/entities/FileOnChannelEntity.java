package si.fri.rso.models.entities;

import si.fri.rso.models.interfaces.MainEntity;

import javax.persistence.*;

@Entity
@Table(name = "file_on_channel")

@NamedNativeQueries({
        @NamedNativeQuery(name = "deleteFilesOnChannel",
                query = "DELETE FROM file_on_channel WHERE file_on_channel.fk_file_id = ?1"),
})

public class FileOnChannelEntity implements MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_file_id")
    private FileEntity fileEntity;

    @Column(name = "channel_id")
    private Integer channelId;

    public Integer getId() {
        return this.id;
    }

    public FileEntity getFileEntity() {
        return this.fileEntity;
    }
    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public Integer getChannelId() {
        return this.channelId;
    }
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

}
