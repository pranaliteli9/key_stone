package com.key_stone.DTO;

import java.time.LocalDateTime;

public class AttachmentResponseDTO {

	private Long id;
	private Long workOrderId;
	private Long uploadedBy;
	private String uploadedByName;
	private String fileName;
	private String filePath;
	private String fileType;
	private Long fileSize;
	private LocalDateTime uploadedAt;

	public AttachmentResponseDTO() {
	}

	public AttachmentResponseDTO(Long id, Long workOrderId, Long uploadedBy, String uploadedByName, String fileName,
			String filePath, String fileType, Long fileSize, LocalDateTime uploadedAt) {

		this.id = id;
		this.workOrderId = workOrderId;
		this.uploadedBy = uploadedBy;
		this.uploadedByName = uploadedByName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.uploadedAt = uploadedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}

	public Long getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(Long uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getUploadedByName() {
		return uploadedByName;
	}

	public void setUploadedByName(String uploadedByName) {
		this.uploadedByName = uploadedByName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
}