package org.cibertec.edu.pe.service.impl;

import java.util.List;

import org.cibertec.edu.pe.dto.CommentRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.entity.Comment;
import org.cibertec.edu.pe.entity.Post;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.repository.CommentRepository;
import org.cibertec.edu.pe.repository.PostRepository;
import org.cibertec.edu.pe.repository.UserRepository;
import org.cibertec.edu.pe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public ResponseDTO findAll() {
		ResponseDTO response = new ResponseDTO();
		try {
			List<Comment> comments = (List<Comment>) commentRepository.findAll();
			response.setData(comments);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("List of all comments");	
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error fetching comments");	
		}
		return response;
	}

	@Override
	public ResponseDTO findById(Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			Comment comment = commentRepository.findById(id).orElse(null);
			if(comment == null) throw new Exception("Comment doesn't exist.");
			response.setData(comment);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("Comment with id: "+id);	
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error fetching comment with id: "+id);	
		}
		return response;
	}

	@Override
	public ResponseDTO findCommentsByUserId(Long userId) {
		ResponseDTO response = new ResponseDTO();
		try {
			List<Comment> comments = (List<Comment>) commentRepository.findByUserId(userId);
			response.setData(comments);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("List of all comments for user with id: "+userId);	
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error fetching comments for user with id: "+userId);	
		}
		return response;
	}

	@Override
	public ResponseDTO findCommentsByPostId(Long postId) {
		ResponseDTO response = new ResponseDTO();
		try {
			List<Comment> comments = (List<Comment>) commentRepository.findByPostId(postId);
			response.setData(comments);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("List of all comments for post with id: "+postId);	
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error fetching comments for post with id: "+postId);	
		}
		return response;
	}

	@Override
	public ResponseDTO createComment(CommentRequestDTO comment) {
		ResponseDTO response = new ResponseDTO();
		try {
			Comment newComment = new Comment();
			newComment.setContent(comment.getContent());
			newComment.setPublicationDate(comment.getPublicationDate());
			User u = userRepository.findById(comment.getUserId()).orElse(null);
			if(u == null) throw new Exception("User doesn't exist.");
			Post p = postRepository.findById(comment.getPostId()).orElse(null);
			if(p == null) throw new Exception("Post doesn't exist.");
			newComment.setUser(u);
			newComment.setPost(p);
			Comment dbComment = commentRepository.save(newComment);
			response.setData(dbComment);
			response.setStatusCode(201);
			response.setError(false);
			response.setMessage("Comment created succesfully");			
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error creating comment");	
		}
		return response;
	}

	@Override
	public ResponseDTO updateComment(Long id, CommentRequestDTO comment) {
		ResponseDTO response = new ResponseDTO();
		try {
			Comment dbComment = commentRepository.findById(id).get();
			dbComment.setContent(comment.getContent());
			dbComment.setPublicationDate(comment.getPublicationDate());
			User u = userRepository.findById(comment.getUserId()).orElse(null);
			if(u == null) throw new Exception("User doesn't exist.");
			Post p = postRepository.findById(comment.getPostId()).orElse(null);
			if(p == null) throw new Exception("Post doesn't exist.");
			dbComment.setUser(u);
			dbComment.setPost(p);
			Comment newComment = commentRepository.save(dbComment);
			response.setData(newComment);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("Comment updated succesfully");
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error updating comment");
		}
		return response;
	}

	@Override
	public ResponseDTO deleteComment(Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			Comment dbComment = commentRepository.findById(id).orElse(null);
			if(dbComment == null) throw new Exception("Comment doesn't exist.");
			commentRepository.delete(dbComment);
			response.setData(dbComment);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("Comment deleted succesfully");
		}catch (Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error deleting comment");
		}
		return response;
	}

}
