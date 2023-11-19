package org.cibertec.edu.pe.controller;

import java.util.List;

import org.cibertec.edu.pe.dto.PostRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.entity.Post;
import org.cibertec.edu.pe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/findAll")
	public List<Post> findAll(){
		return postService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Post findById(@PathVariable Long id) {
		return postService.findById(id);
	}
	
	@GetMapping("/findPostsByUserId/{userId}")
	public List<Post> findPostsByUserId(@PathVariable Long userId) {
		return postService.findPostsByUserId(userId);
	}
	
	@PostMapping("/create")
	public ResponseDTO createPost(@RequestBody PostRequestDTO post) {
		return postService.createPost(post);
	}
	
	@PutMapping("/update/{id}")
	public ResponseDTO updatePost(@PathVariable Long id, @RequestBody PostRequestDTO post) {
		return postService.updatePost(id, post);
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseDTO deletePost(@PathVariable Long id){
		postService.deletePost(id);
        return null;
    }
}
