package org.cibertec.edu.pe.service;

import java.util.List;

import org.cibertec.edu.pe.dto.PostRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.entity.Post;

public interface PostService {
	public List<Post> findAll();
	public Post findById(Long id);
	public List<Post> findPostsByUserId(Long userId);
	public ResponseDTO createPost(PostRequestDTO post);
	public ResponseDTO updatePost(Long id, PostRequestDTO post);
	public ResponseDTO deletePost(Long id);
}
