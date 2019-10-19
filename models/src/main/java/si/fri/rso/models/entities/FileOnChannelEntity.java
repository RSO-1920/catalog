package si.fri.rso.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "file_on_channel")
public class FileOnChannelEntity {

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
