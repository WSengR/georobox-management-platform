package com.geostar.georobox.management.module.document.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_DOCUMENT")
public class DocumentBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_SIZE")
    private String fileSize;

    @Column(name = "FILE_STATE")
    private Short fileState;

    @Column(name = "DATETIME")
    private Date datetime;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return FILE_NAME
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return FILE_PATH
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return FILE_SIZE
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return FILE_STATE
     */
    public Short getFileState() {
        return fileState;
    }

    /**
     * @param fileState
     */
    public void setFileState(Short fileState) {
        this.fileState = fileState;
    }

    /**
     * @return DATETIME
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentBean [id=");
		builder.append(id);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", fileState=");
		builder.append(fileState);
		builder.append(", datetime=");
		builder.append(datetime);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}