package org.cibertec.edu.pe.service;

import org.cibertec.edu.pe.dto.IssueRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;

public interface IssueService {
	public ResponseDTO findAll();
	public ResponseDTO findById(Long id);
	public ResponseDTO createIssue(IssueRequestDTO issue);
	public ResponseDTO updateIssue(Long id, IssueRequestDTO issue);
	public ResponseDTO deleteIssue(Long id);
}
