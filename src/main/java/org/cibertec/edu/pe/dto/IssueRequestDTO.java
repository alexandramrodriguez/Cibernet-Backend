package org.cibertec.edu.pe.dto;

import lombok.Data;

@Data
public class IssueRequestDTO {
	private String content;
	private Long userId;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
