package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.dto.CommentRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.service.CommentService;
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
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/findAll")
	public ResponseDTO findAll(){
		return commentService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseDTO findById(@PathVariable Long id) {
		return commentService.findById(id);
	}
	
	@GetMapping("/findCommentsByUserId/{userId}")
	public ResponseDTO findCommentsByUserId(@PathVariable Long userId) {
		return commentService.findCommentsByUserId(userId);
	}
	
	@GetMapping("/findCommentsByPostId/{postId}")
	public ResponseDTO findCommentsByPostId(@PathVariable Long postId) {
		return commentService.findCommentsByPostId(postId);
	}
	
	@PostMapping("/create")
	public ResponseDTO createComment(@RequestBody CommentRequestDTO comment) {
		return commentService.createComment(comment);
	}
	
	@PutMapping("/update/{id}")
	public ResponseDTO updateComment(@PathVariable Long id, @RequestBody CommentRequestDTO comment) {
		return commentService.updateComment(id, comment);
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseDTO deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }
}
