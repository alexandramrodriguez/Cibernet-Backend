package org.cibertec.edu.pe.service;

import org.cibertec.edu.pe.dto.CommentRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;

public interface CommentService {
	public ResponseDTO findAll();
	public ResponseDTO findById(Long id);
	public ResponseDTO findCommentsByUserId(Long userId);
	public ResponseDTO findCommentsByPostId(Long postId);
	public ResponseDTO createComment(CommentRequestDTO comment);
	public ResponseDTO updateComment(Long id, CommentRequestDTO comment);
	public ResponseDTO deleteComment(Long id);
}
