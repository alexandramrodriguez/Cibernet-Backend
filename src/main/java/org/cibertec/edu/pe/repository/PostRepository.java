package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
	public List<Post> findByUserId(Long userId);
	public Post findByTitle(String title);
}
