package si.fri.rso.lib;

public class FileDTO {
    private Integer fileId;
    private String fileName;
    private String filePath;
    private String fileType;

    public Integer getFileId() {
        return this.fileId;
    };
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
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
