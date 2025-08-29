package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	public List<Comment> findByUserId(Long userId);
	public List<Comment> findByPostId(Long postId);
}
